package br.com.edu.gameoflife;

import javax.swing.JFrame;

import br.com.edu.gameoflife.gui.Window;

/**
 * @author Eduardo
 */
public class MainGameOfLifeRandom {

	private static final double LIFE_RATE = 0.7;

	public static void main(final String[] pArgs) throws Exception {
		final JFrame window = new Window(500, 500, LIFE_RATE);

		window.setVisible(true);
	}

}
