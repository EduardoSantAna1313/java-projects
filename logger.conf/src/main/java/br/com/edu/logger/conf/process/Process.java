package br.com.edu.logger.conf.process;

import org.apache.log4j.Logger;

import br.com.edu.logger.conf.Main;

public class Process implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(Process.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		while (Main.running) {

			try {
				LOGGER.debug(" Process Teste debug");
				LOGGER.info("Process Teste info");
				LOGGER.error("Process Teste error");
				Thread.sleep(1000);
			} catch (final InterruptedException error) {
				LOGGER.error("Ocorreu um erro.", error);
			}

		}

	}

}
