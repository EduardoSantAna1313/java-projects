package atomic.reference;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Exemplo usando atomic reference.
 *
 * @author Eduardo
 */
public class AtomicReferenceExample {

	public static void main(final String[] args) {
		final String oldValue = "old";
		final String newValue = "new";

		final AtomicReference<String> ref = new AtomicReference<>(oldValue);

		System.out.println("Initial value: " + ref.get());

		// se o primeiro parametro (expected) for igual ao valor atual da
		// referencia, a referencia irá alterar o valor para o segundo
		// parametro (newValue)
		if (ref.compareAndSet(oldValue, newValue)) {
			System.out.println("New value: " + ref.get());
		} else {
			System.out.println("Não mudou!");
		}

	}

}
