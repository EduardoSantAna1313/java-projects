package br.com.edu.aws.rekognition;

import java.awt.Color;
import java.util.Map;
import java.util.Scanner;

import br.com.edu.aws.rekognition.service.RekognitionService;
import br.com.edu.aws.rekognition.service.s3.S3Service;
import br.com.edu.aws.rekognition.util.ImageUtil;
import br.com.edu.aws.rekognition.util.TestUtil;

/**
 * Teste individual de imagens. Extração de labels customizadas do Rekognition.
 *
 * @author Eduardo
 */
public class MainTestAllBucket {

	private static final Map<String, Color> COLORS = Map.of(
			// condicao
			"condicao", Color.BLUE,
			// data
			"data", Color.GREEN,
			// numero
			"numero", Color.RED);

	public static void main(final String[] args) {

		final String bucket = TestUtil.getTestProperty("bucket");

		final String prefix = TestUtil.getTestProperty("prefix");

		try (final var service = RekognitionService.getInstance()) {

			// list objects from bucket and prefix
			final var objects = S3Service.getInstance().listObjects(bucket, prefix);

			for (final var s3ObjectSummary : objects) {

				final String key = s3ObjectSummary.getKey();

				System.out.println(key);

				// detect custom labels on Rekognition
				final var labels = service.detectImageCustomLabels(bucket, key);

				for (final var customLabel : labels) {
					System.out.println("\t" + customLabel.name() + ": " + customLabel.confidence().toString());
				}

				final var obj = S3Service.getInstance().getObject(bucket, key);

				// show image with labels
				ImageUtil.show(obj, labels, COLORS);

				next();

			}

		}

		System.exit(0);

	}

	private static void next() {

		System.out.println("Digite para continuar...");

		try {
			final Scanner in = new Scanner(System.in);
			in.nextLine();
		} catch (final Exception error) {
			error.printStackTrace();
		}

	}

}
