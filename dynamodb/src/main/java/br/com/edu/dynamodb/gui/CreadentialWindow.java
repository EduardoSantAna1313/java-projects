package br.com.edu.dynamodb.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author eduardo
 *
 */
public class CreadentialWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel left = new JPanel();

	private JTextField accessKeyFirst = new JTextField();

	private JTextField clientSecretFirst = new JTextField();

	private JPanel right = new JPanel();

	private JTextField accessKeySecond = new JTextField();

	private JTextField clientSecretSecond = new JTextField();

	public CreadentialWindow() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 500);

		this.setLayout(new BorderLayout());

		left.setBackground(Color.red);
		left.setSize(250, 500);
		left.setPreferredSize(new Dimension(250, 500));
		left.setLayout(new GridLayout(10, 2, 5, 5));
		left.add(new JLabel("Access Key"));
		left.add(accessKeyFirst);
		left.add(new JLabel("Client Secret"));
		left.add(clientSecretFirst);

		right.setBackground(Color.blue);
		right.setSize(250, 500);
		right.setPreferredSize(new Dimension(250, 500));
		left.setLayout(new GridLayout(10, 2, 1, 1));
		right.add(new JLabel("Access Key"));
		right.add(accessKeySecond);
		right.add(new JLabel("Client Secret"));
		right.add(clientSecretSecond);

		this.add(left, BorderLayout.WEST);

		this.add(right, BorderLayout.EAST);

		this.setVisible(true);

	}

}
