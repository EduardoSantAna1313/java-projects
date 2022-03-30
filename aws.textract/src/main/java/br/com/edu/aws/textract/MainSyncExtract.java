package br.com.edu.aws.textract;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import br.com.edu.aws.textract.service.SyncOcrService;
import br.com.edu.aws.textract.service.dto.BlockDTO;

/**
 * Class to Sync textract.
 * A extração síncrona só deve ser usada para imagens do tipo jpg, jpeg e png e
 * PDFs de apenas 1 pagina.
 * PDFs com mais de uma página devem usar o método asincrono e o arquivo deve
 * estar armazenado no s3.
 * Arquivos maiores que 5MB tbm devem usar o método asincrono.
 *
 * @author Eduardo
 */
public class MainSyncExtract {

	public static void main(final String[] args) throws IOException {

		final byte[] array = Files.readAllBytes(Path.of("src/test/resources/img0.jpeg"));

		final var blocks = SyncOcrService.getInstance().extract(array);

		blocks.stream().filter(BlockDTO::isLine).map(BlockDTO::getText).forEach(System.out::println);
	}

}
