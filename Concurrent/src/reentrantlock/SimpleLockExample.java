package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Class to {@link ReentrantLock}.
 * O ReentrantLock é mais versátil que o synchronized e possui métodos
 * auxiliares para tentar acessar o objeto como o tryLock e métodos para
 * verificar quantas threads ainda estão na fila para processar.
 *
 * @author Eduardo
 */

public class SimpleLockExample {

	public static void main(final String[] args) {

		final LockedObject obj = new LockedObject();

		final Runnable run = () -> {

			while (true) {
				obj.doSOmethingWithLock();

				try {
					Thread.sleep(100);
				} catch (final InterruptedException error) {
					// NA
				}

			}

		};

		final Thread t1 = new Thread(run);
		final Thread t2 = new Thread(run);
		final Thread t3 = new Thread(run);

		t1.start();
		t2.start();
		t3.start();
	}

}
