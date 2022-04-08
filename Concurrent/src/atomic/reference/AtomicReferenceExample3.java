package atomic.reference;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Exemplo usando atomic reference.
 *
 * @author Eduardo
 */
public class AtomicReferenceExample3 {

	public static void main(final String[] args) {
		final AtomicReference<String> ref = new AtomicReference<>("Initial");

		// retorna o valor, e só depois atualiza
		System.out.println(ref.getAndUpdate(s -> s + "1"));

		// Atualiza o valor e então retorna
		System.out.println(ref.updateAndGet(s -> s + "2"));

	}

}
