
import java.lang.reflect.Field;

public class TesteStringPool {

	public static void main(final String[] args) throws Exception {
		final String s = "HELLO";

		System.out.println("s before magic " + s);
		magic();

		System.out.println("s after magic " + s);
	}

	private static void magic() throws NoSuchFieldException, IllegalAccessException {
		final Field value = String.class.getDeclaredField("value");
		value.setAccessible(true);

		final byte[] bytes = (byte[]) value.get(new String("HELLO"));

		bytes[0] = 'W';
		bytes[1] = 'O';
		bytes[2] = 'R';
		bytes[3] = 'L';
		bytes[4] = 'D';
	}

}
