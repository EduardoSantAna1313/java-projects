/*
 * COPYRIGHT...
 */
package design.patterns.behavioral.memento;

import javax.swing.JTextArea;

/**
 * Class to .
 *
 * @author Eduardo
 */
public class TextAreaWithMemory extends JTextArea {

	/**
	 * long - serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * New instance of TextMemento
	 */
	public TextAreaWithMemory() {
		super();
	}

	/**
	 * New instance of TextMemento
	 *
	 * @param rows
	 * @param columns
	 */
	public TextAreaWithMemory(final int rows, final int columns) {
		super(rows, columns);
	}

	/**
	 * Save memento.
	 *
	 * @return
	 */
	public Memento save() {
		return new TextMemento(getText());
	}

	/**
	 * Restore memento.
	 *
	 * @param oldState
	 */
	public void restore(final TextMemento oldState) {
		this.setText(oldState.getText());
	}

}
