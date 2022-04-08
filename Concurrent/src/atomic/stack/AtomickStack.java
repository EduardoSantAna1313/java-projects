package atomic.stack;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class AtomickStack<T> {

	private final AtomicReference<Node<T>> head = new AtomicReference<>();

	private final AtomicInteger operations = new AtomicInteger(0);

	public void push(final T value) {
		final Node<T> newHeadNode = new Node<>(value);

		while (true) {
			final Node<T> currentHeadNode = head.get();
			newHeadNode.next = currentHeadNode;

			if (head.compareAndSet(currentHeadNode, newHeadNode)) {
				break;
			} else {
				LockSupport.parkNanos(1);
			}

		}

		operations.incrementAndGet();
	}

	public T pop() {
		Node<T> currentHeadNode = head.get();
		Node<T> newHeadNode;

		while (currentHeadNode != null) {
			newHeadNode = currentHeadNode.next;

			if (head.compareAndSet(currentHeadNode, newHeadNode)) {
				break;
			} else {
				LockSupport.parkNanos(1);
				currentHeadNode = head.get();
			}

		}

		operations.incrementAndGet();
		return currentHeadNode != null ? currentHeadNode.value : null;
	}

	public int getCounter() {
		return operations.get();
	}

}