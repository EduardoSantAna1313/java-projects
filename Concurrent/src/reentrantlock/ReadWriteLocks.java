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

				// Utilizando o método tryLock, nao ficamos preso, assim o tryLock retorna false
				// caso o objeto esteja bloqueado para escrita e podemos seguir para a prox
				// operação
				System.out.println(db.getObjectTryLock());

				try {
					Thread.sleep(new Random().nextInt(2500));
				} catch (final InterruptedException error) {
				}

			}

		};

		// se a thread de escrita estiver acessando o objeto, a thread de leitura irá
		// aguardar até a thread de escritar liberar o objeto.
		final Thread t1 = new Thread(runWriting);
		final Thread t2 = new Thread(runReading);

		t1.start();
		t2.start();

	}

}
