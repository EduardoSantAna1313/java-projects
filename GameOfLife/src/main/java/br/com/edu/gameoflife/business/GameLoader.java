/*
 * COPYRIGHT...
 */
package br.com.edu.gameoflife.business;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class to .
 *
 * @author Eduardo
 */
public class GameLoader {

	private GameLoader() {
		// NA
	}

	public static boolean[][] getDefault(final String pFileName) throws IOException {

		final Path path = Paths.get("src/main/resources/").resolve(pFileName);

		final List<boolean[]> list = new ArrayList<>();

		try (final Stream<String> lines = Files.lines(path);) {
			lines.forEach(line -> {
				final boolean[] lineBool = new boolean[line.length()];

				for (int i = 0; i < line.length(); i++) {
					lineBool[i] = 'X' == line.charAt(i);
				}

				list.add(lineBool);
			});
		}

		final boolean[][] tab = new boolean[list.get(0).length][list.size()];
		int index = 0;

		for (final boolean[] a : list) {
			tab[index] = a;
			index++;
		}

		return tab;
	}

}
