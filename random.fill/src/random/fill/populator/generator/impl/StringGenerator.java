/*
 * COPYRIGHT...
 */
package random.fill.populator.generator.impl;

import java.util.List;

import random.fill.populator.generator.AbstractGenerator;

/**
 * Class to .
 *
 * @author Eduardo
 */
public class StringGenerator extends AbstractGenerator {

	@Override
	public String getValue(final Class<?> type) {

		final StringBuilder text = new StringBuilder();

		int stringSize = rand.nextInt(20);

		if (stringSize < 5) {
			stringSize = 5;
		}

		for (int i = 0; i < stringSize; i++) {
			text.append((char) (64 + rand.nextInt(27)));
		}

		return text.toString();
	}

	@Override
	public List<Class<?>> types() {
		return List.of(String.class);
	}

}
