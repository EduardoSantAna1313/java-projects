/*
 * COPYRIGHT...
 */
package design.patterns.behavioral.memento;

/**
 * Class to TextAreaMemento.
 *
 * @author Eduardo
 */
public class TextMemento implements Memento {

	/**
	 * String - text.
	 */
	private final String text;

	/**
	 * New instance of TextAreaMemento
	 *
	 * @param text
	 */
	public TextMemento(final String text) {
		super();
		this.text = text;
	}

	/**
	 * Get text.
	 *
	 * @return
	 */
	public String getText() {
		return text;
	}

}
