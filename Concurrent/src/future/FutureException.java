package future;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FutureException {

	public static void main(String[] args) throws Exception {
		final ExecutorService service = Executors.newSingleThreadExecutor();

		// não lança a exception, é jogada para o future
		final var future = service.submit(() -> {
			throw new IOException("Exception 1"); // Line 1
		});

		try {

			System.out.println(future.get());

		} catch (final Exception e) {

			System.out.println(e.getCause().getMessage().equals("Exception 1"));
		}

		service.shutdown();
		System.out.println("All done");
	}

}
