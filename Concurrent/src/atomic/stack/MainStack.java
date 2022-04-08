package atomic.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainStack {

	public static void main(final String[] args) throws InterruptedException {
		final Stack<Integer> stack = new Stack<>();
		// final LockFreeStack<Integer> stack = new LockFreeStack<>();
		final Random random = new Random();

		for (int i = 0; i < 10000; i++) {
			stack.push(random.nextInt());
		}

		final List<Thread> threads = new ArrayList<>();

		final int pushingThreads = 2;
		final int poppingThreads = 2;

		for (int i = 0; i < pushingThreads; i++) {
			final Thread thread = new Thread(() -> {

				while (true) {
					stack.push(random.nextInt());
				}

			});

			thread.setDaemon(true);
			threads.add(thread);
		}

		for (int i = 0; i < poppingThreads; i++) {
			final Thread thread = new Thread(() -> {

				while (true) {
					stack.pop();
				}

			});

			thread.setDaemon(true);
			threads.add(thread);
		}

		for (final Thread thread : threads) {
			thread.start();
		}

		Thread.sleep(10000);

		System.out.println(String.format("%,d operações realizadas em 10 segundos.", stack.getCounter()));
	}

}