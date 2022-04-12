
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class to .
 *
 * @author Eduardo
 */
public class MainTesteFor {

	public static void main(final String[] args) {
		final List<Integer> l = IntStream.range(0, 200000).boxed().collect(Collectors.toList());

		for (int c = 0; c < 20; c++) {
			testForEach(l);
		}

		System.out.println("\n\n");

		for (int c = 0; c < 20; c++) {
			testeFor(l);
		}

		System.out.println("\n\n");

		for (int c = 0; c < 20; c++) {
			testeIterator(l);
		}

	}

	private static void testeIterator(final List<Integer> l) {
		final var it = l.iterator();

		final LocalDateTime start = LocalDateTime.now();

		int i = 0;

		while (it.hasNext()) {
			i = it.next();
		}

		System.out.println("Iterator: " + start.until(LocalDateTime.now(), ChronoUnit.NANOS));
	}

	private static void testeFor(final List<Integer> l) {
		final LocalDateTime start = LocalDateTime.now();

		int j = 0;

		for (int i = 0; i < l.size(); i++) {
			j = l.get(i);
		}

		System.out.println("Tempo for: " + start.until(LocalDateTime.now(), ChronoUnit.NANOS));
	}

	public static void testForEach(final List<Integer> l) {
		final LocalDateTime start = LocalDateTime.now();

		int i = 0;

		for (final Integer integer : l) {
			i = integer;
		}

		System.out.println("Tempo foreach: " + start.until(LocalDateTime.now(), ChronoUnit.NANOS));
	}

}
