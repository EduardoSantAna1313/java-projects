package br.com.edu.text.similarity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.commons.text.similarity.FuzzyScore;

/**
 * using the fuzzy score algorithm to compare texts.
 *
 * @author Eduardo
 */
public class MainTextSimilarity {

	public static void main(final String[] args) throws IOException {

		final List<String> listOldExtraidos = Arrays.asList(Files.readString(Path.of("src/main/resources/text1.txt")),
				Files.readString(Path.of("src/main/resources/text2.txt")),
				Files.readString(Path.of("src/main/resources/text3.txt")));

		final String novoTextoExtraido = Files.readString(Path.of("src/main/resources/compare.txt"));

		for (final String old : listOldExtraidos) {
			final String term = novoTextoExtraido.replace("\n", "-L-");
			final String query = old.replace("\n", "-L-");

			final FuzzyScore score = new FuzzyScore(new Locale("pt_BR"));
			final int distance = score.fuzzyScore(query, term);

			System.out.println(distance);
		}

	}

}
