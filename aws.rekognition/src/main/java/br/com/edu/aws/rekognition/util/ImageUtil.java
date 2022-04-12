package br.com.edu.aws.rekognition.util;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import com.amazonaws.services.s3.model.S3Object;

import software.amazon.awssdk.services.rekognition.model.CustomLabel;

/**
 * ImageUtil.
 *
 * @author Eduardo
 */
public class ImageUtil {

	private static final JFrame frame = new JFrame("Image show");

	private static final JLabel label = new JLabel();

	/**
	 * New instance of ImageUtil
	 */
	private ImageUtil() {
		// NA
	}

	static {
		frame.setLayout(new FlowLayout());
		frame.add(label);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}

	public static void show(final S3Object object, final List<CustomLabel> labels, final Map<String, Color> colors) {

		frame.setTitle(object.getKey());

		// final BufferedImage img = ImageIO.read(object.getObjectContent());
		final BufferedImage img = getImage(object, labels, colors);
		show(img, labels, colors);

		frame.setVisible(true);
	}

	public static void show(final Path path, final List<CustomLabel> labels, final Map<String, Color> COLORS) {

		try {
			show(ImageIO.read(path.toFile()), labels, COLORS);
		} catch (final IOException error) {
			error.printStackTrace();
		}

	}

	public static BufferedImage getImage(final S3Object object, final List<CustomLabel> labels,
			final Map<String, Color> colors) {

		try {
			final BufferedImage img = ImageIO.read(object.getObjectContent());

			final Graphics2D g = img.createGraphics();

			for (final var label : labels) {
				g.setColor(colors.get(label.name()));

				final var box = label.geometry().boundingBox();

				int x = (int) (box.left() * img.getWidth());
				int y = (int) (box.top() * img.getHeight());

				final int width = (int) (box.width() * img.getWidth());
				final int height = (int) (box.height() * img.getHeight());

				// System.out.println(label.name() + "\t (" + x + ", " + y + ")\t" + width + " "
				// + height);
				g.drawRect(x, y, width, height);

				if (x > img.getWidth()) {
					x = img.getWidth() - 20;
				} else {
					x += 10;
				}

				if (y > img.getHeight()) {
					y = img.getHeight() - 20;
				}

				g.drawString(label.confidence() + "", x, y);
			}

			return img;
		} catch (final Exception error) {
			error.printStackTrace();

			return null;
		}

	}

	public static void show(final BufferedImage image, final List<CustomLabel> labels,
			final Map<String, Color> COLORS) {

		final ImageIcon icon = new ImageIcon(image);
		label.setIcon(icon);

		frame.setSize(image.getWidth(), image.getHeight());

	}

	public static Path save(final Path outputDir, final S3Object obj, final List<CustomLabel> labels,
			final Map<String, Color> colors) {

		final BufferedImage img = getImage(obj, labels, colors);

		final Path out = Path.of(outputDir.toString(), Path.of(obj.getKey()).getFileName().toString());

		if (!out.getParent().toFile().exists()) {
			out.getParent().toFile().mkdirs();
		}

		save(img, out);

		return out;
	}

	public static void save(final BufferedImage image, final Path output) {

		try {
			ImageIO.write(image, "png", output.toFile());
		} catch (final IOException error) {
			error.printStackTrace();
		}

	}

	public static BufferedImage resize(final BufferedImage img, final int newW, final int newH) {
		final Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		final BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_RGB);
	
		final Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
	
		return dimg;
	}

}
