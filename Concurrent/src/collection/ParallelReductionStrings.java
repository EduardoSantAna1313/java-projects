package collection;

import java.util.Arrays;

public class ParallelReductionStrings {

	public static void main(String[] args) {

		final String[] names = { "Anne", "Bob", "Carol", "David", "Evan", "Fred", "George", "Harold", "Ida", "Jack",
				"Kevin" };

		// Quando executado em apenas uma thread a identity do reduce só aparecerá no
		// inicio
		String result = Arrays.stream(names).peek(System.out::print).reduce(",", (s1, s2) -> s1 + s2);

		System.out.println(":\n\t serial String concat = " + result);

		// Quando executado em paralelo, a virgula aparecerá antes de cada palavra, pois
		// temos a função agregadora que irá juntar os resultados de cada operação
		// paralela
		result = Arrays.stream(names).parallel().peek(System.out::print).reduce(",", (s1, s2) -> s1 + s2,
				(s1, s2) -> s1 + s2);

		System.out.println(":\n\t parallel String concat = " + result);

	}
}
