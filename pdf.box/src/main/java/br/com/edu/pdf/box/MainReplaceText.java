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
public class MainReplaceText {

	public static void main(final String[] args) throws IOException {
		final var bytes = Files.readAllBytes(Path.of("src/main/resources/original.pdf"));

		final PdfService serv = new PdfService(bytes);

		serv.replaceText("000000000001", "000000000002");

		serv.save(Path.of("src/main/resources/out.pdf"));
	}

}
