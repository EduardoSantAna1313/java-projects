/**
 * 
 */
package br.com.edu.gameoflife.gui;

import javax.media.opengl.GL2;

/**
 * Utils para o OpenGL.
 * 
 * @author eduardo
 *
 */
public class GLUtils {

	/**
	 * 
	 * Create a new instance of GLUtils
	 */
	private GLUtils() {
		super();
	}

	/**
	 * Draw array of Float
	 * 
	 * @param pGL     gl
	 * @param pMatriz array
	 * @param index   index
	 */
	public static void drawArrayFloat(final GL2 pGL, final boolean[][] pMatriz) {
		for (int i = 0; i < pMatriz.length; i++) {
			for (int j = 0; j < pMatriz[0].length; j++) {
				GLUtils.drawPoint(pGL, i, j, pMatriz[i][j]);
			}
		}
		System.gc();
	}

	/**
	 * Draw line between Point1(x1, y1) and Point2(x2, y2)
	 * 
	 * @param pGL GL2
	 * @param pX  P1.x
	 * @param pY  P1.y
	 * @param x2  P2.x
	 * @param y2  P2.y
	 * @return void
	 */
	private static void drawPoint(final GL2 pGL, final float pX, final float pY, final boolean pVivo) {
		pGL.glBegin(GL2.GL_QUADS);
		if (pVivo) {
			pGL.glColor3b(Byte.MAX_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE);
		} else {
			pGL.glColor3b(Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE);
		}
		pGL.glVertex2f(pY, pX + 1f);
		pGL.glVertex2f(pY + 1f, pX + 1f);
		pGL.glVertex2f(pY + 1f, pX);
		pGL.glVertex2f(pY, pX);
		pGL.glEnd();
	}
}
