package random.fill.populator;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import random.fill.populator.generator.AbstractGenerator;
import random.fill.populator.generator.GeneratorFactory;

/**
 * Preenche aleatoriamente os campos de uma classe.
 *
 * @author Eduardo
 */
public class RandomPopulator {

	public RandomPopulator() {
		// NA
	}

	public void fill(final Object obj) throws ClassNotFoundException {

		final Field[] declaredFields = obj.getClass().getDeclaredFields();

		for (final Field field : declaredFields) {
			field.setAccessible(true);
			final Class<?> type = field.getType();

			Class<?> parameterizedType = null;

			if (field.getGenericType() instanceof ParameterizedType) {
				final ParameterizedType typeP = (ParameterizedType) field.getGenericType();
				final var parameterizedType2 = typeP.getActualTypeArguments()[0];
				parameterizedType = Class.forName(parameterizedType2.getTypeName());
			}

			final Object value = getValue(type, parameterizedType);
			setValue(obj, field, value);
		}

	}

	private static void setValue(final Object obj, final Field field, final Object value) {

		try {
			field.set(obj, value);
		} catch (final IllegalArgumentException e) {
			e.printStackTrace();
		} catch (final IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	private Object getValue(final Class<?> type, final Class<?> parameterizedType) {

		final AbstractGenerator generator = GeneratorFactory.getInstance(type);

		if (generator == null) {
			return null;
		}

		return generator.getValue(type, parameterizedType);
	}

}
