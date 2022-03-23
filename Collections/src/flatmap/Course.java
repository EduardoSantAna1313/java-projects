package flatmap;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Course {

	private final String courseName;

	private final List<Student> studentList;

	public Course(final String courseName, final int studentNumber) {

		this.courseName = courseName;

		this.studentList = Stream.generate(Student::new)
				// limit
				.limit(studentNumber)
				// convert to string
				.map(parseToString(courseName))
				// list
				.collect(Collectors.toList());
	}

	private Function<? super Student, ? extends Student> parseToString(final String courseName) {

		return s -> {

			s.setName(s.getName() + " : " + courseName);
			return s;
		};
	}

	public List<Student> getStudentList() {

		return studentList;
	}

	@Override
	public String toString() {

		return "Course{" + "courseName='" + courseName + '\'' + ",studentList=" + studentList + '}';
	}

}
