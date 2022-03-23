package grouping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Person {

	private static final String[] countries = {
		"BR", "URU", "ITA", "ARG", "ALE", "FRA", "ESP", "ING"
	};

	private static final String[] names = {
		"Ana", "Bruna", "Caroline", "Daniele", "Eduarda"
	};

	private String name;

	private String country;

	private Integer age;

	public Person(final String name, final String country, final Integer age) {

		super();
		this.name = name;
		this.country = country;
		this.age = age;
	}

	public String getName() {

		return name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public String getCountry() {

		return country;
	}

	public void setCountry(final String country) {

		this.country = country;
	}

	public Integer getAge() {

		return age;
	}

	public void setAge(final Integer age) {

		this.age = age;
	}

	@Override
	public String toString() {

		return "Person [name=" + name + ", country=" + country + "]";
	}

	public static List<Person> random(final int max) {

		final var rand = new Random();

		final List<Person> result = new ArrayList<>();

		for (int i = 0; i < max; i++) {

			final String name = names[rand.nextInt(names.length)];
			final String country = countries[rand.nextInt(countries.length)];
			final Integer age = rand.nextInt(50);
			result.add(new Person(name, country, age));
		}

		return result;
	}

}
