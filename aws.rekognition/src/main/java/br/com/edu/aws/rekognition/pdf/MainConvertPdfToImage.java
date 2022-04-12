package br.com.edu.aws.rekognition.pdf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * @author Eduardo
 */
public class MainConvertPdfToImage {

	private static final boolean OVERRIDE = false;

	private static final Path INPUT = Paths.get("/home/edusilva/Desktop/ML2/SourcePdfs/000 - Aleatórias");

	private static final Path OUTPUT = Paths.get("/home/edusilva/Desktop/rekognition/MassaImg/aleatorias");

	public static void main(final String[] args) throws IOException {

		if (OVERRIDE) {
			Files.deleteIfExists(OUTPUT);
		}

		final ExecutorService executor = Executors.newFixedThreadPool(8);

		try (final Stream<Path> list = Files.list(INPUT)) {
			list.forEach(p -> executor.submit(new PdfConverter(p, OUTPUT)));
		}

		executor.shutdown();
	}

}
