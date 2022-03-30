package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Class to {@link ReentrantLock}.
 * O ReentrantLockExamples é mais versátil que o synchronized e possui métodos
 * auxiliares para tentar acessar o objeto como o tryLock.
 *
 * @author Eduardo
 */

public class TryLockExample {

	public static void main(final String[] args) {

		final LockedObject obj = new LockedObject();

		final Runnable run = () -> {

			while (true) {
				obj.doSOmethingWithTryLock();

				try {
					Thread.sleep(100);
				} catch (final InterruptedException error) {
					error.printStackTrace();
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
