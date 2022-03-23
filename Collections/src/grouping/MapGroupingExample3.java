package grouping;

import java.util.TreeMap;
import java.util.stream.Collectors;

public class MapGroupingExample3 {

	public static void main(final String[] args) {

		final var people = Person.random(50);

		// É possivel definir uma factory para o tipo do map
		// neste caso, foi definido um treemap para ordenar as chaves
		final var mapByCountry = people.stream()
				.collect(Collectors.groupingBy(Person::getCountry, TreeMap::new, Collectors.toList()));
		mapByCountry.forEach((k, v) -> System.out.println(k + "\t" + v));
	}

}
