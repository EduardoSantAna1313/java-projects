package br.com.edu.junit.teste.java;

public class ObjectDTO {

	private final String name;

	private final Integer id;

	public ObjectDTO(final String name, final Integer id) {
		super();
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}

}
