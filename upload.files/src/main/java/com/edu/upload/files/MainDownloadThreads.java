package com.edu.upload.files;

import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.edu.upload.files.business.RestApi;
import com.edu.upload.files.type.Environment;
import com.edu.upload.files.util.FileUtils;

/**
 * @author Eduardo
 */
public class MainDownloadThreads {

	private static final Path OUTPUT = Path.of("resources/out/");

	public static void main(final String[] args) throws Exception {

		final ExecutorService service = Executors.newFixedThreadPool(8);

		for (int i = 1001167; i <= 1001766; i++) {
			final int id = i;
			service.submit(() -> call(id));
		}

		service.shutdown();
	}

	private static void call(final Integer fileId) {

		try {
			final Environment env = Environment.load("LOCAL");

			final RestApi api = new RestApi(env);
			final var result = api.get(fileId);

			final Path outputFile = OUTPUT.resolve(result.getFileName());

			if (!outputFile.getParent().toFile().exists()) {
				outputFile.getParent().toFile().mkdirs();
			}

			FileUtils.save(result.getContent(), outputFile);

			System.out.println("Arquivo " + outputFile + " criado!");
		} catch (final Exception error) {
			//
		}

	}

}
