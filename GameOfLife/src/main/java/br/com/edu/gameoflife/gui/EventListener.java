/**
 *
 */
package br.com.edu.gameoflife.gui;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import br.com.edu.gameoflife.business.GameOfLifeBusiness;

/**
 * @author Eduardo
 */
public class EventListener implements GLEventListener {

	/**
	 * tab - Tabuleiro</br>
	 */
	private final GameOfLifeBusiness tab;

	/**
	 * Create a new instance of EventListener
	 *
	 * @param pTabuleiro
	 */
	public EventListener(final GameOfLifeBusiness pTabuleiro) {
		this.tab = pTabuleiro;
	}

	/**
	 * init.
	 */
	@Override
	public void init(final GLAutoDrawable pDrawable) {
		final GL2 gl = pDrawable.getGL().getGL2();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
	}

	/**
	 * display.
	 */
	@Override
	public void display(final GLAutoDrawable pDrawable) {
		final GL2 gl = pDrawable.getGL().getGL2();
		GLUtils.drawArrayFloat(gl, tab.getMatriz());
	}

	/**
	 * reshape.
	 */
	@Override
	public void reshape(final GLAutoDrawable pDrawable, final int pX, final int pY, final int pWidth,
			final int pHeight) {
		final GL2 gl = pDrawable.getGL().getGL2();
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(0, tab.getMatriz().length, 0, tab.getMatriz()[0].length, -1, 1);
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
	}

	/**
	 * dispose.
	 */
	@Override
	public void dispose(final GLAutoDrawable pDrawable) {
		// NA
	}

}