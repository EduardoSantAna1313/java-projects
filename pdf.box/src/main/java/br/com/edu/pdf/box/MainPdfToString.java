package br.com.edu.pdf.box;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import br.com.edu.pdf.box.service.PdfService;

/**
 * Remove anotações de PDF.
 *
 * @author Eduardo
 */
public class MainPdfToString {

	public static void main(final String[] args) throws IOException {
		final var bytes = Files.readAllBytes(Path.of("src/main/resources/out.pdf"));

		final PdfService serv = new PdfService(bytes);
		System.out.println(serv.toString());
	}

}
