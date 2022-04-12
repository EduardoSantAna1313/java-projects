package br.com.edu.aws.rekognition;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import br.com.edu.aws.rekognition.service.RekognitionService;
import br.com.edu.aws.rekognition.service.s3.S3Service;
import br.com.edu.aws.rekognition.util.ImageUtil;
import br.com.edu.aws.rekognition.util.TestUtil;
import software.amazon.awssdk.services.rekognition.model.CustomLabel;

/**
 * Teste de extração de labels customizadas do Rekognition de um bucket inteiro
 * e filtro por prefix e salvando resultado.
 *
 * @author Eduardo
 */
public class MainTestAllBucketSave {

	private static final Map<String, Color> COLORS = Map.of(
			// condicao
			"condicao", Color.BLUE,
			// data
			"data", Color.GREEN,
			// numero
			"numero", Color.RED);

	private static final Path OUTPUT_DIR = Path.of("/home/edusilva/Desktop/rekognition/tests_result/");

	private static final Path OUTPUT_RESULT = Path.of("/home/edusilva/Desktop/rekognition/tests_result/json");

	public static void main(final String[] args) {

		final String bucket = TestUtil.getTestProperty("bucket");

		final String prefix = TestUtil.getTestProperty("prefix");

		try (final var service = RekognitionService.getInstance()) {

			// list objects from bucket and prefix
			final var objects = S3Service.getInstance().listObjects(bucket, prefix);

			for (final var s3ObjectSummary : objects) {

				final String key = s3ObjectSummary.getKey();

				if (!key.endsWith(".png")) {
					continue;
				}

				System.out.println(key);

				// detect custom labels on Rekognition
				final var labels = service.detectImageCustomLabels(bucket, key);

				for (final var customLabel : labels) {
					System.out.println("\t" + customLabel.name() + ": " + customLabel.confidence().toString());
				}

				final var obj = S3Service.getInstance().getObject(bucket, key);

				if (obj == null) {
					continue;
				}

				// show image with labels
				final var fileName = ImageUtil.save(OUTPUT_DIR, obj, labels, COLORS);

				System.out.println("Saved " + fileName);

				save(OUTPUT_RESULT, obj.getKey(), labels);

			}

		}

	}

	private static void save(final Path outputResult, final String key, final List<CustomLabel> labels) {
		final Gson gson = new Gson();

		final var json = gson.toJson(labels);

		try {
			String string = Path.of(key).getFileName().toString();

			string = string.substring(0, string.lastIndexOf('.'));
			string += ".json";

			final Path out = Path.of(outputResult.toString(), string);

			if (!out.getParent().toFile().exists()) {
				out.getParent().toFile().mkdirs();
			}

			Files.write(out, json.getBytes(), StandardOpenOption.CREATE);
		} catch (final IOException error) {
			error.printStackTrace();
		}

	}

}
