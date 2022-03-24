package br.com.edu.image.similarity;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Eduardo
 */
public class ImageUtil {

	private static final JFrame frame = new JFrame();

	/**
	 * Create a new instance of ImageUtil
	 */
	private ImageUtil() {
		// NA
	}

	public static void show(final BufferedImage... pImage) {

		for (final BufferedImage img : pImage) {
			final ImageIcon icon = new ImageIcon(resize(img, 90, 70));
			final JLabel label = new JLabel();
			label.setIcon(icon);
			frame.add(label);
		}

		frame.setLayout(new FlowLayout());
		frame.setSize(200, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void close() throws Exception {
		Thread.sleep(1500);
		frame.dispose();
	}

	public static BufferedImage getImage(final String pName) throws Exception {
		final Path path = Paths.get(pName);

		final byte[] bytes = Files.readAllBytes(path);

		return ImageUtil.convert(bytes);
	}

	public static BufferedImage convert(final byte[] pBytes) throws Exception {
		final ByteArrayInputStream inputStream = new ByteArrayInputStream(pBytes);

		return ImageIO.read(inputStream);
	}

	public static BufferedImage toGrayScale(final BufferedImage pImage) {

		final ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
		final ColorConvertOp op = new ColorConvertOp(cs, null);

		return op.filter(pImage, null);
	}

	public static byte[] getBytes(final BufferedImage pImage) {
		return ((DataBufferByte) pImage.getData().getDataBuffer()).getData();
	}

	public static void showImage(final byte[] pImage, final String title) throws IOException {
		final ByteArrayInputStream bis = new ByteArrayInputStream(pImage);
		final BufferedImage image = ImageIO.read(bis);
		showImage(image, title);
	}

	public static void showImage(final BufferedImage pImage, final String pTitle) {

		final JFrame frame = new JFrame();
		frame.setTitle(pTitle);
		frame.setLayout(new FlowLayout());
		frame.setSize(200, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final ImageIcon icon = new ImageIcon(pImage);
		final JLabel lbl = new JLabel();
		lbl.setIcon(icon);
		frame.add(lbl);
	}

	public static byte[] toBitmap(final BufferedImage image) throws IOException {

		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(image, "BMP", out);

		return out.toByteArray();
	}

	public static BufferedImage resize(final BufferedImage img, final int newW, final int newH) {
		final Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		final BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		final Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

}