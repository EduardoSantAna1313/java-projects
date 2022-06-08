package br.com.edu.aws.rekognition;

import br.com.edu.aws.rekognition.service.RekognitionService;

public class MainTestCollections {

	public static void main(final String[] args) {

		try (final var service = RekognitionService.getInstance()) {
			service.collection();

		}

	}

}
