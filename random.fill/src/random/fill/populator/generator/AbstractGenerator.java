/*
 * COPYRIGHT...
 */
package random.fill.populator.generator;

import java.util.List;
import java.util.Random;

/**
 * Class to .
 *
 * @author Eduardo
 */
public abstract class AbstractGenerator {

	protected static final Random rand = new Random();

	public Object getValue(final Class<?> type, final Class<?> parametrized) {

		if (parametrized == null) {
			return getValue(type);
		}

		return null;
	}

	public abstract Object getValue(final Class<?> type);

	public abstract List<Class<?>> types();

}
