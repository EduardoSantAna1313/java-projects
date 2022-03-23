package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

public class CompletableFutureExamples {

	public static void main(String... args) throws InterruptedException {
		final CompletableFuture<String> c1 = new CompletableFuture<>();
		c1.handle(callback());
		new Thread(() -> {
			try {
				c1.complete("OK");
			} catch (Exception ex) {
				c1.completeExceptionally(ex);
			}
		}).start();

		Thread.sleep(1000);
	}

	private static BiFunction<String, Throwable, String> callback() {
		return (content, ex) -> {
			if (ex == null) {
				System.out.println("Conteudo = " + content);
			} else {
				ex.printStackTrace();
			}
			return "";
		};
	}

}
