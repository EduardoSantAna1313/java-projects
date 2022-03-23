package interrupt;

import java.math.BigInteger;

/**
 * Exemplo de interrupção de thread.
 * Se no método run não for validado se a thread foi interrompida, a thread
 * continuará executando até o calculo ser finalizado. O código
 * computation.interrupt() NÃO mata a thread, por isso a verificação é
 * necessária.
 *
 * @author Eduardo
 */
public class ThreadInterrupt {

	public static void main(final String[] args) throws InterruptedException {

		final VeryLargeComputation computation = new VeryLargeComputation(3_000, 3_123_34);
		computation.start();

		Thread.sleep(100);

		computation.interrupt();

		System.out.println(computation.getResult());

	}

	static class VeryLargeComputation extends Thread {

		private BigInteger result;

		private final long base;

		private final long pow;

		public VeryLargeComputation(final long base, final long pow) {

			super();
			this.base = base;
			this.pow = pow;

			result = BigInteger.valueOf(base);
		}

		@Override
		public void run() {

			for (int i = 1; i < pow; i++) {

				if (this.isInterrupted()) {

					result = BigInteger.valueOf(-1);
				}

				result = result.multiply(BigInteger.valueOf(base));

			}

		}

		public BigInteger getResult() {

			return result;
		}

	}

}
