package br.com.edu.gameoflife;

import javax.swing.JFrame;

import br.com.edu.gameoflife.business.GameLoader;
import br.com.edu.gameoflife.gui.Window;

/**
 * @author Eduardo
 */
public class MainGameOfLife {

	public static void main(final String[] pArgs) throws Exception {
		JFrame window;

		if (pArgs != null && pArgs.length == 3) {
			window = new Window(Integer.valueOf(pArgs[0]), Integer.valueOf(pArgs[1]), Double.valueOf(pArgs[2]));
		} else if (pArgs != null && pArgs.length == 1) {
			final boolean[][] game = GameLoader.getDefault(pArgs[0]);

			window = new Window(game);
		} else {
			window = new Window(200, 200, 0.5);
		}

		window.setVisible(true);
	}

}
