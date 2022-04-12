package br.com.edu.pdf.box;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;

import br.com.edu.pdf.box.service.PdfService;

/**
 * Cria pdf a partir de texto
 *
 * @author Eduardo
 */
public class MainCreatePdfFromText {

	public static void main(final String[] args) throws IOException {

		final String text = "Teste pdf texto";

		try (final var doc = PdfService.create(new StringReader(text))) {

			final PdfService service = new PdfService(doc);

			service.encrypt();

			service.save(Path.of("src/main/resources/out.pdf"));
		}

	}

}
