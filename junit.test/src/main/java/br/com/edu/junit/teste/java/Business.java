package br.com.edu.junit.teste.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Business layer de teste.
 *
 * @author Eduardo
 */
public class Business {

	private List<String> list;

	/**
	 * Create a new instance of Business
	 */
	public Business() {
		super();

		getList().add("bla");
	}

	/**
	 * Retrieve the value of list.
	 *
	 * @return the list
	 */
	public List<String> getList() {

		if (list == null) {
			list = new ArrayList<>();
		}

		return list;
	}

	/**
	 * Set a new value to list.
	 *
	 * @param list
	 *                 the list to set
	 */
	public void setList(final List<String> list) {
		this.list = list;
	}

	public static double dividir(final double dividendo, final double divisor) throws Exception {

		if (divisor == 0) {
			throw new ArithmeticException("O divisor nao posse ser zero.");
		}

		return dividendo / divisor;
	}

}
