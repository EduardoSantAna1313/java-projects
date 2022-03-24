package br.com.edu.tesseract;

import java.nio.file.Path;

import br.com.edu.tesseract.service.TessService;

/**
 * Corrige inclinação da imagem.
 *
 * @author Eduardo
 */
public class MainImageDeskew {

	public static void main(final String[] args) throws Exception {
		final TessService business = new TessService(Path.of("src/main/resources/notas/nota04.pdf"))
				// exibe imagem no form
				.showForm(true)
				// corrige angulação
				.deskew(true)
				// apply threshold
				.applyThreshold(false);

		System.out.println(business.doOcr());
	}

}
