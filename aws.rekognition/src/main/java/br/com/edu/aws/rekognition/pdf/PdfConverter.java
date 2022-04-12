package br.com.edu.aws.rekognition.pdf;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import br.com.edu.aws.rekognition.util.ImageUtil;

class PdfConverter implements Runnable {

	private final Path nfsePath;

	private final Path outputPath;

	public PdfConverter(final Path path, final Path oUTPUT) {
		super();
		this.nfsePath = path;
		outputPath = oUTPUT;
	}

	@Override
	public void run() {

		final String fileName = nfsePath.getFileName().toString().replace(".pdf", ".png");

		final Path outputFile = outputPath.resolve(fileName);
		outputFile.getParent().toFile().mkdirs();

		try (final PDDocument document = PDDocument.load(nfsePath.toFile())) {

			document.getPage(0).setAnnotations(null);

			final PDFRenderer pdfRenderer = new PDFRenderer(document);

			BufferedImage bi = pdfRenderer.renderImageWithDPI(0, 80, ImageType.RGB);

			bi = ImageUtil.resize(bi, 496, 702);

			System.out.println(outputFile);

			ImageIO.write(bi, "png", outputFile.toFile());

		} catch (final IOException error) {
			error.printStackTrace();
		}

	}

}