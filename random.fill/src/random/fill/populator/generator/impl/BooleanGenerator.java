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
public class BooleanGenerator extends AbstractGenerator {

	@Override
	public Boolean getValue(final Class<?> type) {
		return rand.nextBoolean();
	}

	@Override
	public List<Class<?>> types() {
		return List.of(Boolean.class, boolean.class);
	}

}
