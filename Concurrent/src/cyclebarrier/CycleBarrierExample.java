package cyclebarrier;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Eduardo
 */
public class CycleBarrierExample {

	public static void main(final String[] args) throws Exception {

		// cria um cyclic barrier que espera 4 "partes"
		final CyclicBarrier barrier = new CyclicBarrier(4, () -> System.out.println("Confirming step is complete"));

		final Callable<Boolean> callable = () -> {

			step(1);

			// aguarda até todas as "partes" (threads) completarem a tarefa "step"
			barrier.await();

			step(2);

			// aguarda até todas as "partes" (threads) completarem a tarefa "step"
			barrier.await();

			step(3);

			// aguarda até todas as "partes" (threads) completarem a tarefa "step"
			barrier.await();

			return true;
		};

		// service com as 4 threads
		// se o numero max do pool de threads for menor que o cyclic barrier a aplicação
		// ficará travada
		final ExecutorService service = Executors.newFixedThreadPool(2);
		service.invokeAll(List.of(callable, callable, callable, callable));

		System.out.println("Shutting service down");
		service.shutdown();
	}

	public static void step(final int stepNo) throws InterruptedException {

		final int ms = new Random().nextInt(5) * 1000;
		System.out.println(
				Thread.currentThread().getName() + " waiting for " + ms + " milliseconds to start step " + stepNo);
		Thread.sleep(ms);
		System.out.println(Thread.currentThread().getName() + " completed step " + stepNo);
	}

}
