package reentrantlock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * O recurso nunca estará bloqueado se estiver sendo acesso para a leitura,
 * porém se o recurso estiver bloquedo para escrita com um {@link WriteLock}, a
 * leitura também será bloqueada a menos que se utilize o método tryLock, assim
 * o método retornará false. Porém o lock não será adquirido para a leitura.
 *
 * @author Eduardo
 */
public class ReadWriteLocks {

	public static void main(final String[] args) {

		final Database db = new Database();

		final Runnable runWriting = () -> {

			while (true) {
				db.setObject(String.valueOf(System.currentTimeMillis()));

				try {
					Thread.sleep(400);
				} catch (final InterruptedException error) {
				}

			}

		};

		final Runnable runReading = () -> {

			while (true) {
				System.out.println(db.getObjectTryLock());

				try {
					Thread.sleep(new Random().nextInt(2500));
				} catch (final InterruptedException error) {
				}

			}

		};
		final Thread t1 = new Thread(runWriting);
		final Thread t2 = new Thread(runReading);

		t1.start();
		t2.start();

	}

}
