/*
 * COPYRIGHT...
 */
package random.fill.populator.generator.impl;

import java.math.BigDecimal;
import java.util.List;

import random.fill.populator.generator.AbstractGenerator;

/**
 * Class to .
 *
 * @author Eduardo
 */
public class BigDecimalGenerator extends AbstractGenerator {

	@Override
	public BigDecimal getValue(final Class<?> type) {
		return BigDecimal.valueOf(rand.nextDouble());
	}

	@Override
	public List<Class<?>> types() {
		return List.of(BigDecimal.class);
	}

}
