package atomic.reference;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Exemplo usando atomic reference.
 *
 * @author Eduardo
 */
public class AtomicReferenceExample2 {

	public static void main(final String[] args) {
		final String oldValue = "old";
		final String newValue = "new";

		final AtomicReference<String> ref = new AtomicReference<>(oldValue);

		System.out.println("Initial value: " + ref.get());

		ref.set("Unexpcted value");

		// Não irá alterar pois "Unexpcted value" != oldValue
		if (ref.compareAndSet(oldValue, newValue)) {
			System.out.println("New value: " + ref.get());
		} else {
			System.out.println("Não mudou!");
		}

	}

}
