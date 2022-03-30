package br.com.edu.aws.textract;

import br.com.edu.aws.textract.service.AsyncOcrService;
import br.com.edu.aws.textract.service.dto.BlockDTO;
import br.com.edu.aws.textract.util.TestUtil;

/**
 * Async textract test;
 *
 * @author Eduardo
 */
public class MainAsyncExtract {

	public static void main(final String[] args) throws Exception {

		final var bucket = TestUtil.getTestProperty("bucket");
		final var key = TestUtil.getTestProperty("key");

		final var service = AsyncOcrService.getInstance();

		final var jobId = service.extract(bucket, key);

		System.out.println("Staring extract to " + key + " jobId: " + jobId);

		while (!service.finished(jobId)) {
			System.out.print('.');

			try {
				Thread.sleep(500);
			} catch (final InterruptedException error) {
				// NA
			}

		}

		System.out.println("\n\nTextract:");

		final var blocks = service.getBlocks(jobId);

		blocks.stream().filter(BlockDTO::isLine)
				//
				.map(b -> b.getText() + "\t" + b.getConfidence())
				// prin
				.forEach(System.out::println);
	}

}
