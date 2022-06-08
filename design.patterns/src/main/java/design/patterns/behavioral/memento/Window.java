package design.patterns.behavioral.memento;

import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;

	private final Button buttonPrevious = new Button("<<");

	private final Button buttonNext = new Button(">>");

	private final TextAreaWithMemory textArea = new TextAreaWithMemory(10, 80);

	private final Button buttonSave = new Button("Save");

	public Window() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setTitle("Memento");

		this.setLayout(new BorderLayout());
		this.add(buttonPrevious, BorderLayout.WEST);
		this.add(textArea, BorderLayout.CENTER);
		this.add(buttonNext, BorderLayout.EAST);
		this.add(buttonSave, BorderLayout.SOUTH);

		final Caretaker taker = new Caretaker();

		buttonSave.addActionListener(e -> taker.add(textArea.save()));

		buttonPrevious.addActionListener(e -> {
			final TextMemento restore = (TextMemento) taker.previous();
			textArea.restore(restore);
			textArea.requestFocusInWindow();
		});

		buttonNext.addActionListener(e -> {
			final TextMemento restore = (TextMemento) taker.next();
			textArea.restore(restore);
			textArea.requestFocusInWindow();
		});

		this.setVisible(true);
	}

	public static void main(final String[] args) {
		new Window();

	}

}
