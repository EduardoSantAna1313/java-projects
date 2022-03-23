package random.fill;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Random;

/**
 * Preenche aleatoriamente os campos de uma classe.
 *
 * @author Eduardo
 */
public class RandomPopulator {

	private static final Random rand = new Random();

	public RandomPopulator() {
		// NA
	}

	public void fill(final Object obj) {

		final Field[] declaredFields = obj.getClass().getDeclaredFields();

		for (final Field field : declaredFields) {
			field.setAccessible(true);
			final Class<?> type = field.getType();
			final Object value = getValue(type);
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

	private Object getValue(final Class<?> type) {

		if (type.isAssignableFrom(Boolean.class) || type.isAssignableFrom(boolean.class)) {
			return rand.nextBoolean();
		}

		if (type.isAssignableFrom(Integer.class) || type.isAssignableFrom(int.class)) {
			return rand.nextInt();
		}

		if (type.isAssignableFrom(Float.class) || type.isAssignableFrom(float.class)) {
			return rand.nextFloat();
		}

		if (type.isAssignableFrom(Double.class) || type.isAssignableFrom(double.class)) {
			return rand.nextDouble();
		}

		if (type.isAssignableFrom(BigDecimal.class)) {
			return BigDecimal.valueOf(rand.nextDouble());
		}

		if (type.isAssignableFrom(String.class)) {
			final StringBuilder text = new StringBuilder();

			for (int i = 0; i < rand.nextInt(20); i++) {
				text.append((char) (64 + rand.nextInt(27)));
			}

			return text.toString();
		}

		final Object o = instanciateObject(type);

		fill(o);

		return o;
	}

	private static Object instanciateObject(final Class<?> type) {

		try {
			final Constructor<?>[] constructor = type.getConstructors();

			if (constructor == null || constructor.length < 1) {
				return null;
			}

			return constructor[0].newInstance();
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
