package damon;

public class ThreadDaemonExample {

	public static void main(String[] args) throws InterruptedException {
		final Thread t1 = new Thread(() -> {

			while (true) {
				System.out.println("bla");
			}

		});

		// se a t1 nao é setada como Daemon, ela ficará executando eternamente
		// quando setamos uma thread como Daemon ela será encerrada quando a Thread
		// principal encerrar.
		t1.setDaemon(true);
		t1.start();

		Thread.sleep(100);
	}

}
