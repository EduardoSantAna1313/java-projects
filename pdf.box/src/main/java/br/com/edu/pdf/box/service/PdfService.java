package br.com.edu.pdf.box.service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdfwriter.ContentStreamWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.tools.TextToPDF;

public class PdfService {

	private final PDDocument document;

	public PdfService(final byte[] bytes) throws IOException {
		document = PDDocument.load(bytes);
	}

	public PdfService(final PDDocument document) {
		this.document = document;
	}

	public static PDDocument create(final StringReader reader) throws IOException {
		final TextToPDF parser = new TextToPDF();
		return parser.createPDFFromText(reader);
	}

	public void removeAnnotations() {
		document.getPages().forEach(p -> p.setAnnotations(null));
	}

	public void encrypt() throws IOException {

		final int keyLength = 128;

		final AccessPermission ap = new AccessPermission();

		ap.setCanPrint(false);

		final StandardProtectionPolicy spp = new StandardProtectionPolicy("12345", "", ap);
		spp.setEncryptionKeyLength(keyLength);
		spp.setPermissions(ap);
		document.protect(spp);
	}

	public void removeEncrypt() {

		if (document.isEncrypted()) {
			document.setAllSecurityToBeRemoved(true);
		}

	}

	public void replaceText(final Map<String, String> values) {
		values.forEach((k, v) -> {

			try {
				replaceText(k, v);
			} catch (final IOException error) {
				error.printStackTrace();
			}

		});
	}

	public void replaceText(final String searchString, final String replacement) throws IOException {

		final PDPageTree pages = document.getDocumentCatalog().getPages();

		for (final PDPage page : pages) {
			final PDFStreamParser parser = new PDFStreamParser(page);
			parser.parse();
			final List<Object> tokens = parser.getTokens();

			for (int j = 0; j < tokens.size(); j++) {
				final Object next = tokens.get(j);

				if (next instanceof Operator) {
					final Operator op = (Operator) next;

					if (op.getName().equals("Tj")) {
						final COSString previous = (COSString) tokens.get(j - 1);
						String string = previous.getString();

						if (string.contains(searchString)) {
							string = string.replaceFirst(searchString, replacement);
							previous.setValue(string.getBytes());
						}

					} else if (op.getName().equals("TJ")) {
						final COSArray previous = (COSArray) tokens.get(j - 1);

						for (int k = 0; k < previous.size(); k++) {
							final Object arrElement = previous.getObject(k);

							if (arrElement instanceof COSString) {
								final COSString cosString = (COSString) arrElement;
								String string = cosString.getString();

								if (string.contains(searchString)) {

									string = string.replace(searchString, replacement);
									cosString.setValue(string.getBytes());
								}

							}

						}

					}

				}

			}

			final PDStream updatedStream = new PDStream(document);
			final OutputStream out = updatedStream.createOutputStream();
			final ContentStreamWriter tokenWriter = new ContentStreamWriter(out);
			tokenWriter.writeTokens(tokens);
			page.setContents(updatedStream);
			out.close();
		}

	}

	@Override
	public String toString() {
		return toString(0, document.getNumberOfPages());
	}

	public String toString(final int start, final int end) {

		try {
			final PDFTextStripper reader = new PDFTextStripper();
			reader.setStartPage(start);
			reader.setEndPage(end);
			return reader.getText(document);
		} catch (final IOException error) {
			error.printStackTrace();
		}

		return null;
	}

	public void save(final Path out) throws IOException {

		if (out.getParent() != null) {
			final var parent = out.getParent().toFile();

			if (!parent.exists()) {
				parent.mkdirs();
			}

		}

		document.save(out.toFile());

		document.close();
	}

}
