package schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Exemplo usnado ScheduledExecutorService.
 *
 * @author Eduardo
 */
public class ScheduleExample {

	private static int index = 0;

	public static void main(final String[] args) throws Exception {

		final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

		final Runnable worker = () -> {

			System.out.println("teste" + index);
			index++;
		};

		// Define um worker, um delay inicial, um delay entre operação
		service.scheduleWithFixedDelay(worker, 2, 2, TimeUnit.SECONDS);

		while (index < 5) {

			Thread.sleep(100);
		}

		service.shutdown();
	}

}
