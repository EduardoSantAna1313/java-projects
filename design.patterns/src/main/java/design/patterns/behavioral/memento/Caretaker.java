/*
 * COPYRIGHT...
 */
package design.patterns.behavioral.memento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class to .
 *
 * @author Eduardo
 */
public class Caretaker {

	private final List<Memento> mementos = new ArrayList<>();

	private int currentPosition = -1;

	public List<Memento> getMementos() {
		return Collections.unmodifiableList(mementos);
	}

	public void add(final Memento memento) {
		mementos.add(memento);
		currentPosition = mementos.size() - 1;
	}

	public Memento previous() {
		currentPosition--;

		if (currentPosition < 0) {
			currentPosition = 0;
		}

		return mementos.get(currentPosition);
	}

	public Memento next() {
		currentPosition++;

		if (currentPosition >= mementos.size()) {
			currentPosition = mementos.size() - 1;
		}

		return mementos.get(currentPosition);
	}

}
