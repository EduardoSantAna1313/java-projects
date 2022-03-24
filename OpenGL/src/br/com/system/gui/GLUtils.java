package br.com.system.gui;

import java.awt.Font;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2ES3;
import com.jogamp.opengl.util.awt.TextRenderer;

import br.com.system.constant.Constants;

/**
 * @author Eduardo
 */
public class GLUtils {

	private static int width = Constants.SCREEN_WIDTH;

	private static int height = Constants.SCREEN_HEIGHT;

	/**
	 * New instance of GLUtils
	 */
	private GLUtils() {
		// NA
	}

	/**
	 * Draw the Grid
	 *
	 * @param gl
	 *               gl
	 */
	public static void drawGrid(final GL2 gl) {
		// Draw Background
		gl.glBegin(GL2ES3.GL_QUADS);
		gl.glColor4fv(Constants.BACKGROUND_COLOR, 0);
		gl.glVertex2f(-height, -width);
		gl.glVertex2f(height, -width);
		gl.glVertex2f(height, width);
		gl.glVertex2f(-height, width);
		gl.glEnd();
		// Draw Vertical Lines of Grid
		gl.glBegin(GL.GL_LINES);

		for (int i = -height; i < height; i++) {
			gl.glColor4fv(Constants.DIVISION_LINES_COLOR, 0);
			gl.glVertex2f(i, width);
			gl.glVertex2f(i, -width);
			gl.glColor4fv(Constants.TICK_LINES_COLOR, 0);
			gl.glVertex2f(i, height);
			gl.glVertex2f(i, -height);
		}

		gl.glEnd();
		// Draw Horizontal Lines of Grid
		gl.glBegin(GL.GL_LINES);

		for (int i = -width; i < width; i++) {
			gl.glColor4fv(Constants.DIVISION_LINES_COLOR, 0);
			gl.glVertex2f(height, i);
			gl.glVertex2f(-height, i);
			gl.glColor4fv(Constants.TICK_LINES_COLOR, 0);
			gl.glVertex2f(width, i);
			gl.glVertex2f(-width, i);
		}

		gl.glEnd();
	}

	/**
	 * Draw text
	 *
	 * @param text
	 *                 text
	 * @param x
	 *                 x
	 * @param y
	 *                 y
	 */
	public static void drawText(final String text, final int x, final int y, final int z, final float scale) {
		final TextRenderer textRenderer = new TextRenderer(new Font("Arial", Font.PLAIN, 20));
		textRenderer.setUseVertexArrays(false);
		textRenderer.setSmoothing(false);
		textRenderer.beginRendering(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		textRenderer.setColor(Constants.TEXT_COLOR);
		textRenderer.draw(text, x, y);
		textRenderer.draw3D(text, x, y, z, scale);
		textRenderer.endRendering();
		textRenderer.flush();
		textRenderer.dispose();
	}

	/**
	 * Draw array of Float
	 *
	 * @param gl
	 *                  gl
	 * @param array
	 *                  array
	 * @param index
	 *                  index
	 */
	public static void drawArrayFloat(final GL2 gl, final float[] array) {
		float x1;
		float y1;
		float x2;
		float y2;

		for (int i = 1; i < array.length; i++) {
			x1 = (i - 1) * Constants.UNITS_WIDE / array.length;
			y1 = array[i - 1];
			x2 = i * Constants.UNITS_WIDE / array.length;
			y2 = array[i];
			GLUtils.drawPoint(gl, x1, y1, x2, y2);
		}

	}

	/**
	 * Draw line between Point1(x1, y1) and Point2(x2, y2)
	 *
	 * @param gl
	 *               GL2
	 * @param x1
	 *               P1.x
	 * @param y1
	 *               P1.y
	 * @param x2
	 *               P2.x
	 * @param y2
	 *               P2.y
	 *
	 * @return void
	 */
	private static void drawPoint(final GL2 gl, final float x1, final float y1, final float x2, final float y2) {
		gl.glBegin(GL.GL_LINES);
		gl.glColor3b(Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MIN_VALUE);
		gl.glVertex2d(x1, y1);
		gl.glVertex2d(x2, y2);
		gl.glEnd();
	}

}
