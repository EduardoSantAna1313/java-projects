/*
 * COPYRIGHT...
 */
package br.com.edu.aws.s3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.amazonaws.services.s3.model.S3ObjectSummary;

import br.com.edu.aws.s3.storage.StorageObject;
import br.com.edu.aws.s3.storage.StorageService;
import br.com.edu.aws.s3.util.StorageUtil;

/**
 * Class to .
 *
 * @author Eduardo
 */
public class MainUploadFolder {

	private static final boolean OVERRIDE = true;

	private static AtomicInteger count = new AtomicInteger(0);

	public static void main(final String[] args) throws Exception {

		final var executor = Executors.newFixedThreadPool(8);

		final var bucket = StorageUtil.getTestProperty("bucket_name");

		final StorageService service = StorageService.getInstance();
		final var list = service.listObjects(bucket, "training/");

		try (final var stream = Files
				.list(Path.of("/home/edusilva/Desktop/rekognition/treinamento-2022-05-11/train/"));) {

			stream.sorted((p, p2) -> Long.compare(p.toFile().length(), p2.toFile().length()))
					.forEach(p -> executor.submit(() -> {
						upload(p, list);
					}));

		}

		executor.shutdown();

	}

	private static void upload(final Path p, final List<S3ObjectSummary> list) {

		final var bucket = StorageUtil.getTestProperty("bucket_name");

		if (!OVERRIDE) {
			final boolean has = list.stream()
					.anyMatch(obj -> Path.of(obj.getKey()).getFileName().toString().equals(p.getFileName().toString()));

			if (has) {
				System.err.println(p.getFileName() + " já existe!");
				return;
			}

		}

		final StorageObject object = new StorageObject();
		object.setBucket(bucket);
		object.setKey("training/" + p.getFileName().toString());

		final File file = p.toFile();

		try {
			object.setIs(new FileInputStream(file));
		} catch (final FileNotFoundException error) {
			error.printStackTrace();
		}

		object.setContentLength(file.length());

		final StorageService service = StorageService.getInstance();

		final var result = service.putObject(object);

		System.out.println(count.incrementAndGet() + "\tuploaded " + result.getKey() + "\t" + result.getMd5());
	}

}
