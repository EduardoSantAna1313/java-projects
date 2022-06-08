package br.com.edu.java.proxy.model;

import br.com.edu.java.proxy.db.dao.PO;

/**
 * Model to user.
 *
 * @author Eduardo
 */
public class User extends PO {

	public static String tableName = "User";

	private Integer id;

	private String name;

	/**
	 * New instance of MUser
	 */
	public User() {
		super();
	}

	/**
	 * New instance of MUser
	 *
	 * @param id
	 * @param name
	 */
	public User(final Integer id, final String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("MUser [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}
