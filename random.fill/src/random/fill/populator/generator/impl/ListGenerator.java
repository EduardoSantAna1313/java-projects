/*
 * COPYRIGHT...
 */
package random.fill.populator.generator.impl;

import java.util.ArrayList;
import java.util.List;

import random.fill.populator.generator.AbstractGenerator;
import random.fill.populator.generator.GeneratorFactory;

/**
 * Class to .
 *
 * @author Eduardo
 */
public class ListGenerator extends AbstractGenerator {

	@SuppressWarnings("unchecked")
	@Override
	public Object getValue(final Class<?> type, final Class<?> parametrized) {

		@SuppressWarnings("rawtypes")
		final List parameters = new ArrayList<>();

		int listSize = rand.nextInt(20);

		if (listSize < 1) {
			listSize = 1;
		}

		for (int i = 0; i < listSize; i++) {
			final AbstractGenerator generator = GeneratorFactory.getInstance(parametrized);

			if (generator != null) {
				parameters.add(generator.getValue(parametrized));
			}

		}

		return parameters;
	}

	@Override
	public Object getValue(final Class<?> type) {
		return null;
	}

	@Override
	public List<Class<?>> types() {
		return List.of(List.class);
	}

}
