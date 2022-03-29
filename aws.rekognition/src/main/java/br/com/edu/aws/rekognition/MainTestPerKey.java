package br.com.edu.aws.rekognition;

import java.awt.Color;
import java.util.Map;

import br.com.edu.aws.rekognition.service.RekognitionService;
import br.com.edu.aws.rekognition.service.s3.S3Service;
import br.com.edu.aws.rekognition.util.ImageUtil;
import br.com.edu.aws.rekognition.util.TestUtil;
import software.amazon.awssdk.services.rekognition.model.CustomLabel;

/**
 * Teste individual de imagens. Extração de labels customizadas do Rekognition.
 *
 * @author Eduardo
 */
public class MainTestPerKey {

	private static final Map<String, Color> COLORS = Map.of(
			// condicao
			"condicao", Color.BLUE,
			// data
			"data", Color.GREEN,
			// numero
			"numero", Color.RED);

	public static void main(final String[] args) {

		final String bucket = TestUtil.getTestProperty("bucket");
		final String key = TestUtil.getTestProperty("key");

		try (final var service = RekognitionService.getInstance()) {

			final var labels = service.detectImageCustomLabels(bucket, key);

			System.out.println(key);

			for (final CustomLabel customLabel : labels) {
				System.out.println("\t" + customLabel.name() + ": " + customLabel.confidence().toString());
			}

			final var obj = S3Service.getInstance().getObject(bucket, key);
			ImageUtil.show(obj, labels, COLORS);
		}

	}

}
