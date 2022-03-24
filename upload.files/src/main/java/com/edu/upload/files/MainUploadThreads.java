package com.edu.upload.files;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.edu.upload.files.business.RestApi;
import com.edu.upload.files.business.stream.UploadChangeListener;
import com.edu.upload.files.type.Environment;
import com.edu.upload.files.util.FileUtils;

/**
 * @author Eduardo
 */
public class MainUploadThreads {

	private static final File FILE = new File("resources/teste0.pdf");

	public static void main(final String[] args) throws Exception {

		final var service = Executors.newFixedThreadPool(8);

		final var id = new AtomicInteger(0);

		for (int i = 0; i < 500; i++) {
			service.submit(() -> call(id.incrementAndGet()));
		}

		service.shutdown();

	}

	private static void call(final int id) {

		try {
			final Environment env = Environment.load("LOCAL");

			final String fileName = id + "_" + FILE.getName().replaceAll("[^A-Za-z0-9\\.]", "");

			final long contentLenght = FILE.length();

			System.out.println("Uploading " + fileName + " " + FileUtils.format(contentLenght));

			final UploadChangeListener listener = percent -> {
			};

			final var api = new RestApi(env);
			final var response = api.post(fileName, new FileInputStream(FILE), contentLenght, listener);

			System.out.println(response);

		} catch (final Exception error) {
			// NA
		}

	}

}
