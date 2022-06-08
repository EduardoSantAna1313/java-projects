package br.com.edu.tesseract.util;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import org.apache.commons.io.output.ByteArrayOutputStream;

/**
 * This class contains utility methods for transforming image data.
 *
 * @author Eduardo
 */

public class ImageUtils {

	private static final JFrame frame = new JFrame();

	private static final JLabel label = new JLabel();

	private ImageUtils() {
		// NA
	}

	public static void show(final BufferedImage pImage) throws Exception {
		final ImageIcon icon = new ImageIcon(pImage);

		frame.setLayout(new FlowLayout());
		frame.setSize(200, 300);
		label.setIcon(icon);
		frame.add(label);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void close() throws Exception {
		Thread.sleep(1500);
		frame.dispose();
	}

	public static void showImage(final BufferedImage pImage) throws Exception {

		final int cols = frame.getComponentCount();

		frame.setTitle("Frame");
		frame.setLayout(new GridLayout(1, cols));
		frame.setSize(900, 900);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		final ImageIcon icon = new ImageIcon(resize(pImage, 350, 520));
		final JLabel lbl = new JLabel();
		lbl.setIcon(icon);
		frame.add(lbl);
		frame.revalidate();
	}

	public static void showImage(final BufferedImage pImage, final String pTitle) throws Exception {

		final int cols = frame.getComponentCount();

		frame.setTitle("Frame");
		frame.setLayout(new GridLayout(1, cols));
		frame.setSize(900, 540);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		final ImageIcon icon = new ImageIcon(resize(pImage, 350, 520));
		final JLabel lbl = new JLabel();
		lbl.setIcon(icon);
		frame.add(lbl);
		frame.revalidate();
	}

	public static int[][] gsArray(final BufferedImage img) {
		int[][] gs = null;
		final int height = img.getHeight();
		final int width = img.getWidth();

		if (height > 0 && width > 0) {
			gs = new int[height][width];

			for (int i = 0; i < height; i++) {

				for (int j = 0; j < width; j++) {
					final int bits = img.getRGB(j, i);
					// Not sure if precision is needed, but adding for now
					final long avg = Math.round(((bits >> 16 & 0xff) + (bits >> 8 & 0xff) + (bits & 0xff)) / 3.0);
					gs[i][j] = (int) avg;
				}

			}

		}

		return gs;
	}

	public static BufferedImage gsImg(final int[][] raw) {
		BufferedImage img = null;
		final int height = raw.length;
		final int width = raw[0].length;

		if (height > 0 && width > 0) {
			img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

			for (int i = 0; i < height; i++) {

				for (int j = 0; j < width; j++) {
					img.setRGB(j, i, raw[i][j] << 16 | raw[i][j] << 8 | raw[i][j]);
				}

			}

		}

		return img;
	}

	public static BufferedImage getImage(final String pName) throws Exception {
		final Path path = Paths.get(pName);

		final byte[] bytes = Files.readAllBytes(path);

		return convert(bytes);
	}

	public static BufferedImage convert(final byte[] pBytes) throws Exception {
		final ByteArrayInputStream inputStream = new ByteArrayInputStream(pBytes);

		return ImageIO.read(inputStream);
	}

	public static BufferedImage toGrayScale(final BufferedImage pImage) throws Exception {

		final ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
		final ColorConvertOp op = new ColorConvertOp(cs, null);

		return op.filter(pImage, null);
	}

	public static byte[] getBytes(final BufferedImage pImage) throws Exception {
		return ((DataBufferByte) pImage.getData().getDataBuffer()).getData();
	}

	public static void showImage(final byte[] pImage, final String title) throws Exception {
		final ByteArrayInputStream bis = new ByteArrayInputStream(pImage);
		final BufferedImage image = ImageIO.read(bis);
		showImage(image, title);
	}

	public static byte[] toBitmap(final BufferedImage image) throws Exception {

		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(image, "BMP", out);

		return out.toByteArray();
	}

	public static BufferedImage resize(final BufferedImage img, final int newW, final int newH) {
		final Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		final BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_BYTE_GRAY);

		final Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

	public static BufferedImage applyThreshold(final int[][] pixels, final int threshold, final boolean pInvert) {

		final BufferedImage im = new BufferedImage(pixels[0].length, pixels.length, BufferedImage.TYPE_BYTE_BINARY);

		for (int x = 0; x < pixels[0].length; x++) {

			for (int y = 0; y < pixels.length; y++) {

				if (pixels[y][x] > threshold) {
					im.setRGB(x, y, pInvert ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
				} else {
					im.setRGB(x, y, pInvert ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
				}

			}

		}

		return im;
	}

}