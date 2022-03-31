package random.fill.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToStringUtil {

	private ToStringUtil() {

	}

	public static String toString(final Object obj, final String separator) {

		final var fields = obj.getClass().getDeclaredFields();

		final List<String> sfields = new ArrayList<>();

		for (final Field field : fields) {

			field.setAccessible(true);

			final StringBuilder result = new StringBuilder();
			result.append(field.getName());
			result.append(": ");

			try {
				result.append(field.get(obj));
			} catch (final Exception error) {
				error.printStackTrace();
			}

			sfields.add(result.toString());
		}

		return sfields.stream().collect(Collectors.joining(separator));
	}

}
