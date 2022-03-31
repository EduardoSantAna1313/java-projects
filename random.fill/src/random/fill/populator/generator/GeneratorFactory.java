/*
 * COPYRIGHT...
 */
package random.fill.populator.generator;

import java.util.List;

import random.fill.util.ClassLoaderUtil;

/**
 * Class to .
 *
 * @author Eduardo
 */
public class GeneratorFactory {

	private static final List<AbstractGenerator> list;

	private GeneratorFactory() {
		// NA
	}

	static {
		list = ClassLoaderUtil.loadGenerators();
	}

	public static AbstractGenerator getInstance(final Class<?> clazz) {

		for (final AbstractGenerator gen : list) {

			final var types = gen.types();

			for (final Class<?> type : types) {

				if (clazz.isAssignableFrom(type)) {
					return gen;
				}

			}

		}

		return new GenericGenerator();

	}

}
