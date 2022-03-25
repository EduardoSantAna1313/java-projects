package br.com.edu.chain.resposability.business.bo;

public class TesteBO {

	private Integer id;

	private String name;

	private String description;

	private String help;

	private Integer fk1;

	private Integer fk2;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public Integer getFk1() {
		return fk1;
	}

	public void setFk1(Integer fk1) {
		this.fk1 = fk1;
	}

	public Integer getFk2() {
		return fk2;
	}

	public void setFk2(Integer fk2) {
		this.fk2 = fk2;
	}

}
