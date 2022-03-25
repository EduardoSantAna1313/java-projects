package br.com.edu.chain.resposability.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Eduardo
 */
public class ValidationHandler<T> {

	private List<String> messages;

	private ValidationHandler<T> next;

	public ValidationHandler<T> getNext() {
		return next;
	}

	public ValidationHandler<T> setNext(final ValidationHandler<T> next) {
		this.next = next;
		return this;
	}

	protected List<String> getMessages() {

		if (messages == null) {
			messages = new ArrayList<>();
		}

		return messages;
	}

	public void addMessage(final String message) {
		this.getMessages().add(message);
	}

	public List<String> process(final T bo) {

		if (this.getNext() != null) {
			this.getMessages().addAll(this.getNext().process(bo));
			return this.getMessages();
		}

		return Collections.emptyList();
	}

}
