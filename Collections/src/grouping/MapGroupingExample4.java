package grouping;

import java.util.TreeMap;
import java.util.stream.Collectors;

public class MapGroupingExample4 {

	public static void main(final String[] args) {

		final var people = Person.random(100);

		// define um map de nomes por idade e por país
		final var mapByCountry = people.stream().collect(
				// agrupa pessoas por país e define um map ordenado
				Collectors.groupingBy(Person::getCountry, TreeMap::new,
						// agrupa as idades por nome
						Collectors.groupingBy(Person::getName, TreeMap::new,
								Collectors.mapping(Person::getAge, Collectors.toList()))));

		mapByCountry.forEach((k, v) -> {

			System.out.println(k);

			// imprime o nome das pessoas e a lista de idades
			v.forEach((k1, v1) -> System.out.println("\t" + k1 + ": " + v1));
		});
	}

}
