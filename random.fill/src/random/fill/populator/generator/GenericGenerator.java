package random.fill.populator.generator;

import java.lang.reflect.Constructor;
import java.util.List;

import random.fill.populator.RandomPopulator;

public class GenericGenerator extends AbstractGenerator {

	@Override
	public List<Class<?>> types() {
		return null;
	}

	@Override
	public Object getValue(final Class<?> type) {

		try {
			final Constructor<?>[] constructor = type.getConstructors();

			if (constructor == null || constructor.length < 1) {
				return null;
			}

			final Object newInstance = constructor[0].newInstance();

			final RandomPopulator populator = new RandomPopulator();
			populator.fill(newInstance);
			return newInstance;
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
