/*
 * COPYRIGHT...
 */
package design.patterns.behavioral.memento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/**
 * Class to .
 *
 * @author Eduardo
 */
public class TestMemento {

	@Test
	public void testMemento() {
		final Caretaker taker = new Caretaker();

		taker.add(new TextMemento("Text1"));
		taker.add(new TextMemento("text2"));
		taker.add(new TextMemento("text3"));

		TextMemento text = (TextMemento) taker.previous();
		assertEquals("text2", text.getText());

		text = (TextMemento) taker.previous();
		assertEquals("Text1", text.getText());

		text = (TextMemento) taker.next();
		text = (TextMemento) taker.next();
		text = (TextMemento) taker.next();
		text = (TextMemento) taker.next();
		assertEquals("text3", text.getText());

		text = (TextMemento) taker.previous();
		text = (TextMemento) taker.previous();
		text = (TextMemento) taker.previous();
		text = (TextMemento) taker.previous();
		text = (TextMemento) taker.previous();
		text = (TextMemento) taker.previous();
		text = (TextMemento) taker.previous();
		assertEquals("Text1", text.getText());

		taker.add(new TextMemento("text4"));
		text = (TextMemento) taker.next();
		assertEquals("text4", text.getText());

	}

}
