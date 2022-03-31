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
public class DoubleGenerator extends AbstractGenerator {

	@Override
	public Double getValue(final Class<?> type) {
		return rand.nextDouble();
	}

	@Override
	public List<Class<?>> types() {
		return List.of(Double.class, double.class);
	}

}
