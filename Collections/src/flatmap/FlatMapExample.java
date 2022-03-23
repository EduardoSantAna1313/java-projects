package flatmap;

import java.util.List;

public class FlatMapExample {

	public static void main(final String[] args) {

		final List<Course> courses = List.of(new Course("Geometry 101", 3), new Course("Java 101", 5),
				new Course("History 101", 4));

		System.out.println("Lista de cursos ");
		courses.forEach(System.out::println);

		System.out.println("\nFlat map of students: ");
		courses.stream().flatMap(course -> course.getStudentList().stream()).forEach(System.out::println);
	}

}
