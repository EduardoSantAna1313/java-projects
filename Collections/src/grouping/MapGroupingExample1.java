package grouping;

import java.util.stream.Collectors;

public class MapGroupingExample1 {

	public static void main(final String[] args) {

		final var people = Person.random(50);

		// agrupa as pessoas por country
		final var mapByCountry = people.stream().collect(Collectors.groupingBy(Person::getCountry));
		mapByCountry.forEach((k, v) -> System.out.println(k + "\t" + v));
	}

}
