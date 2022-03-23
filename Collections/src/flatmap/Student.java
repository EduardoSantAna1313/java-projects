package flatmap;

import java.util.Random;

class Student {

	private final String[] namesArray = {
		"Allen", "Bob", "Caleb", "Don", "Fred", "Greg", "Howard", "Ira", "James", "Kevin"
	};

	private String name;

	private static int lastId = 1000;

	private int studentId;

	{

		final int i = new Random().nextInt(10);
		this.name = namesArray[i];
		this.studentId = ++lastId;
	}

	public String getName() {

		return this.name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public int getStudentId() {

		return studentId;
	}

	public void setStudentId(final int studentId) {

		this.studentId = studentId;
	}

	@Override
	public String toString() {

		return "[" + this.studentId + " : " + this.name + "] ";
	}

}