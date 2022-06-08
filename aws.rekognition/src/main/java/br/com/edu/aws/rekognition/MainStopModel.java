package br.com.edu.aws.rekognition;

import br.com.edu.aws.rekognition.service.RekognitionService;

/**
 * Class to Stop the model.
 *
 * @author Eduardo
 */
public class MainStopModel {

	public static void main(final String[] args) {

		try (final var service = RekognitionService.getInstance()) {
			service.stopModel();

			while (!service.stoped()) {

				try {
					Thread.sleep(10_000);
				} catch (final InterruptedException error) {
					error.printStackTrace();
				}

			}

		}

	}

}
