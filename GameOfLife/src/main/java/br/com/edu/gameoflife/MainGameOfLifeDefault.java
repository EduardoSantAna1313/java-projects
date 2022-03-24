package br.com.edu.gameoflife;

import javax.swing.JFrame;

import br.com.edu.gameoflife.business.GameLoader;
import br.com.edu.gameoflife.gui.Window;

/**
 * @author Eduardo
 */
public class MainGameOfLifeDefault {

	public static void main(final String[] pArgs) throws Exception {

		final JFrame window = new Window(GameLoader.getDefault("default2.txt"));

		window.setVisible(true);
	}

}
