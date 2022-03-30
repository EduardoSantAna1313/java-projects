package reentrantlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * É possivel criar um {@link ReentrantReadWriteLock}.
 * Os ReadLock podem nao bloquear acesso. Apenas os writelock.
 * Se uma thread adiquire o WriteLock, o ReadLock estará bloqueado!
 *
 * @author Eduardo
 */
public class ReadOnlyLock {

	public static void main(final String[] args) {

		final Database db = new Database();

		final Runnable runReading = () -> {

			while (true) {
				System.out.println(db.getObject());

				try {
					Thread.sleep(100);
				} catch (final InterruptedException error) {
				}

			}

		};
		final Thread t1 = new Thread(runReading);
		final Thread t2 = new Thread(runReading);
		final Thread t3 = new Thread(runReading);

		t1.start();
		t2.start();
		t3.start();

	}

}
