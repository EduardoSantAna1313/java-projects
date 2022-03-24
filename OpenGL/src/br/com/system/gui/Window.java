package br.com.system.gui;

import java.awt.Adjustable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import br.com.system.constant.Constants;

/**
 * @author Eduardo
 */
public class Window extends JFrame implements AdjustmentListener {

	private static final long serialVersionUID = 5758879940366369084L;

	private final Cube listener = new Cube();

	private final JScrollBar scrollX;

	private final JScrollBar scrollY;

	/**
	 * New instance of Window
	 */
	public Window() {

		setTitle("OpenGL");
		this.setSize(new Dimension(Constants.SCREEN_HEIGHT + 50, Constants.SCREEN_WIDTH + 50));
		setPreferredSize(new Dimension(550, 550));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final GLProfile glprofile = GLProfile.getDefault();
		final GLCapabilities glcapabilities = new GLCapabilities(glprofile);
		final GLCanvas glcanvas = new GLCanvas(glcapabilities);

		glcanvas.addGLEventListener(listener);
		glcanvas.setPreferredSize(new Dimension(600, 600));

		final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
		animator.start();

		scrollX = new JScrollBar(Adjustable.HORIZONTAL, 0, 0, -100, 100);
		scrollX.addAdjustmentListener(this);

		scrollY = new JScrollBar(Adjustable.VERTICAL, 1, 1, -100, 100);
		scrollY.addAdjustmentListener(this);

		final JPanel south = new JPanel();
		south.setLayout(new GridLayout(2, 1));
		south.add(scrollX);

		final JPanel background = new JPanel();
		background.setLayout(new BorderLayout());
		background.add(glcanvas, BorderLayout.CENTER);
		background.add(south, BorderLayout.SOUTH);
		background.add(scrollY, BorderLayout.EAST);
		this.add(background);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.awt.event.AdjustmentListener#adjustmentValueChanged(java.awt.event.
	 * AdjustmentEvent)
	 */
	@Override
	public void adjustmentValueChanged(final AdjustmentEvent evt) {

		if (scrollX.equals(evt.getSource())) {
			listener.setRotateX(evt.getValue());
		}

		if (scrollY.equals(evt.getSource())) {
			listener.setRotateY(evt.getValue());
		}

	}

}
