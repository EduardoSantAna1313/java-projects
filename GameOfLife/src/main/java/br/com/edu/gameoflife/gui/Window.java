package br.com.edu.gameoflife.gui;

import java.awt.Dimension;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import com.jogamp.opengl.util.Animator;

import br.com.edu.gameoflife.business.GameOfLifeBusiness;
import br.com.edu.gameoflife.constant.Constants;

/**
 * @author Eduardo
 */
public class Window extends JFrame implements Runnable {

	/**
	 * long - serialVersionUID.
	 */
	private static final long serialVersionUID = 748857151096726344L;

	/**
	 * tab - Tabuleiro</br>
	 */
	public final transient GameOfLifeBusiness jogo;

	/**
	 * Create a new instance of Window
	 *
	 * @param pMatriz
	 */
	public Window(final boolean[][] pMatriz) {
		jogo = new GameOfLifeBusiness(pMatriz);

		initGui();
	}

	/**
	 * Create a new instance of Window
	 *
	 * @param pLargura
	 * @param pAltura
	 * @param pPopulacao
	 *
	 * @throws Exception
	 */
	public Window(final int pLargura, final int pAltura, final double pPopulacao) throws Exception {
		jogo = new GameOfLifeBusiness(pLargura, pAltura, pPopulacao);

		initGui();
	}

	/**
	 * Inicia interface gráfica.
	 */
	private void initGui() {
		this.setTitle("Jogo da Vida");
		this.setSize(new Dimension(Constants.SCREEN_HEIGHT + 50, Constants.SCREEN_WIDTH + 50));
		this.setPreferredSize(new Dimension(150, 150));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		final GLProfile glprofile = GLProfile.getDefault();
		final GLCapabilities glcapabilities = new GLCapabilities(glprofile);
		final GLCanvas glcanvas = new GLCanvas(glcapabilities);
		glcanvas.addGLEventListener(new EventListener(jogo));
		glcanvas.setPreferredSize(new Dimension(400, 400));

		final Animator animator = new Animator(glcanvas);
		animator.setUpdateFPSFrames(1, null);
		animator.start();

		this.add(glcanvas);

		this.setTitle(getTitleFormatted());
	}

	/**
	 * Retorna o titulo formatado com as rodadas e o numero de vivos.
	 *
	 * @return
	 */
	private String getTitleFormatted() {
		return "Jogo da vida - Rodada " + jogo.getRodada() + " Vivos: " + jogo.getQtdeVivo();
	}

	/**
	 * Seta a visibilidade e inicia a thread.
	 */
	@Override
	public void setVisible(final boolean pVisible) {
		super.setVisible(pVisible);
		new Thread(this).start();
	}

	/**
	 * run thread.
	 */
	@Override
	public void run() {

		while (jogo.getQtdeVivo() > 0) {
			jogo.proximaRodada();
			this.setTitle(getTitleFormatted());

			try {
				Thread.sleep(100);
			} catch (final Exception error) {
				error.printStackTrace();
			}

		}

		System.out.println("fim");
	}

}
