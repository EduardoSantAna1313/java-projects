package atomic.stack;

class Stack<T> {

	private Node<T> head;

	private int counter = 0;

	public synchronized void push(final T value) {
		final Node<T> newHead = new Node<>(value);
		newHead.next = head;
		head = newHead;
		counter++;
	}

	public synchronized T pop() {

		if (head == null) {
			counter++;
			return null;
		}

		final T value = head.value;
		head = head.next;
		counter++;
		return value;
	}

	public int getCounter() {
		return counter;
	}

}