package br.com.edu.aws.rekognition;

import br.com.edu.aws.rekognition.service.RekognitionService;

/**
 * Class to Start the model.
 *
 * @author Eduardo
 */
public class MainStartModel {

	public static void main(final String[] args) {

		try (final var service = RekognitionService.getInstance()) {
			service.startModel();

			while (!service.started()) {

				try {
					Thread.sleep(15_000);
				} catch (final InterruptedException error) {
					error.printStackTrace();
				}

			}

		}

	}

}
