package br.com.system.gui;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;

import br.com.system.constant.Constants;

/**
 * @author Eduardo
 */
public class EventListener implements GLEventListener {

	private static Logger log = Logger.getLogger(EventListener.class.getName());

	private static float[] array = new float[100];

	private float rotate = 0f;

	private float scale = 1f;

	@Override
	public void init(final GLAutoDrawable drawable) {
		log.log(Level.INFO, "Init");
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		Arrays.fill(array, 0, 100, 1.5f);
	}

	@Override
	public void display(final GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		// Clear The Screen And The Depth Buffer
		gl.glLoadIdentity(); // Reset The View

		// triangle rotation
		gl.glRotatef(rotate, 0, 50, 0);
		gl.glScalef(scale, scale, scale);

		// Drawing Using Triangles
		gl.glBegin(GL.GL_TRIANGLES);

		gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
		gl.glVertex3f(0.5f, 0.7f, 0.0f); // Top
		gl.glColor3f(0.0f, 1.0f, 0.0f); // blue
		gl.glVertex3f(-0.2f, -0.50f, 0.0f); // Bottom Left
		gl.glColor3f(0.0f, 0.0f, 1.0f); // green
		gl.glVertex3f(0.5f, -0.5f, 0.0f); // Bottom Right

		gl.glEnd();
		gl.glFlush();

	}

	@Override
	public void reshape(final GLAutoDrawable drawable, final int x, final int y, final int width, final int height) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(Constants.MIN, Constants.MAX, Constants.MIN, 10, Constants.MIN, 10);
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
	}

	@Override
	public void dispose(final GLAutoDrawable drawable) {
		log.log(Level.INFO, "Exit");
	}

	public void setRotate(final float rotate) {
		this.rotate = 360f * rotate / 100f;
	}

	public void setScale(final float scale) {
		float v = 100f / scale;

		if (v == 0) {
			v = 1f;
		}

		this.scale = -v;
	}

}