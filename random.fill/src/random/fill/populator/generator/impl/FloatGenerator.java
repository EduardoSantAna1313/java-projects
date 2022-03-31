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
public class FloatGenerator extends AbstractGenerator {

	@Override
	public Float getValue(final Class<?> type) {
		return rand.nextFloat();
	}

	@Override
	public List<Class<?>> types() {
		return List.of(Float.class, float.class);
	}

}
