package com.edu.upload.files;

import java.nio.file.Path;

import com.edu.upload.files.business.RestApi;
import com.edu.upload.files.type.Environment;
import com.edu.upload.files.util.FileUtils;

/**
 * @author Eduardo
 */
public class MainDownload {

	private static final Path OUTPUT = Path.of("resources/out/");

	public static void main(final String[] args) throws Exception {
		final Environment env = Environment.load("LOCAL");

		final Integer id = 1002376;

		final RestApi api = new RestApi(env);
		final var result = api.get(id);

		System.out.println(
				"Downloading file " + result.getFileName() + " : " + FileUtils.format(result.getContentLength()));

		final Path outputFile = OUTPUT.resolve(result.getFileName());

		if (!outputFile.getParent().toFile().exists()) {
			outputFile.getParent().toFile().mkdirs();
		}

		FileUtils.save(result.getContent(), outputFile);

		System.out.println("Arquivo " + outputFile + " criado!");
	}

}
