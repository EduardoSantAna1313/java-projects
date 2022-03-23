package grouping;

import java.util.stream.Collectors;

public class MapGroupingExample2 {

	public static void main(final String[] args) {

		final var people = Person.random(50);

		// Retorna os distinc names por country
		final var mapByCountry = people.stream().collect(
				Collectors.groupingBy(Person::getCountry, Collectors.mapping(Person::getName, Collectors.toSet())));
		mapByCountry.forEach((k, v) -> System.out.println(k + "\t" + v));
	}

}
