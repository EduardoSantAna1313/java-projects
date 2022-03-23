package deadlock;

/**
 * Exemplo de deadlock. Duas threads tentam consumir o mesmo recurso ao mesmo
 * tempo.
 *
 * @author Eduardo
 */
public class ThreadDeadLockExample {

	public static void main(final String[] args) {

		final var commonResource = new Resource();

		final var worker1 = new ThreadDeadLock(commonResource);
		final var worker2 = new ThreadDeadLock(commonResource);
		worker1.start();
		worker2.start();
	}

	static class ThreadDeadLock extends Thread {

		private final Resource resource;

		public ThreadDeadLock(final Resource reosurce) {

			super();
			this.resource = reosurce;
		}

		@Override
		public void run() {

			resource.doSomething();
		}

	}

	static class Resource {

		synchronized void doSomething() {

			try {

				System.out.println("Iniciando com a " + Thread.currentThread().getName());
				Thread.sleep(3_000);
				System.out.println("Saindo " + Thread.currentThread().getName() + "\n\n");
			} catch (final InterruptedException e) {

				// NA
			}

		}

	}

}
