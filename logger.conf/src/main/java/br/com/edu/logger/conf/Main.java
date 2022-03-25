package br.com.edu.logger.conf;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import br.com.edu.logger.conf.process.Process;

public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class);

	public static boolean running = true;

	public static void main(final String[] args) throws InterruptedException {

		LOGGER.debug("debug Hello World!");
		LOGGER.info("Info Hello World!");
		final Main a = new Main();

		try {

			LOGGER.info("Chamando o método teste");

			a.teste();

		} catch (final Exception e) {
			LOGGER.error("Ocorreu um erro no método teste", e);
		}

		final ExecutorService service = Executors.newFixedThreadPool(10);

		for (int i = 0; i < 10; i++) {
			service.submit(new Process());
		}

		Thread.sleep(10_000);

		running = false;
		service.shutdown();

	}

	public void teste() {
		teste2();
	}

	public void teste2() {
		teste3();
	}

	public void teste3() {
		teste4();
	}

	public void teste4() {
		teste5();
	}

	public void teste5() {
		final double a = 10 / 0;
		LOGGER.debug("A = " + a);
	}

}
