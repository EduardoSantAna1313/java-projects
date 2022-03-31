package random.fill;

import random.fill.populator.RandomPopulator;
import random.fill.test.Obj;

/**
 * Preenche aleatoriamente os campos de uma classe.
 *
 * @author Eduardo
 */
public class MainRandomFill {

	public static void main(final String[] args) throws ClassNotFoundException {

		final Obj obj = new Obj();

		final RandomPopulator pop = new RandomPopulator();
		pop.fill(obj);

		System.out.println(obj);
	}

}
