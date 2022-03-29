package br.com.edu.aws.rekognition.util;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
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
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}

	public static void show(final S3Object object, final List<CustomLabel> labels, final Map<String, Color> COLORS) {

		try {
			frame.setTitle(object.getKey());

			final BufferedImage img = ImageIO.read(object.getObjectContent());
			show(img, labels, COLORS);
		} catch (final IOException error) {
			error.printStackTrace();
		}

	}

	public static void show(final Path path, final List<CustomLabel> labels, final Map<String, Color> COLORS) {

		try {
			show(ImageIO.read(path.toFile()), labels, COLORS);
		} catch (final IOException error) {
			error.printStackTrace();
		}

	}

	public static void show(final BufferedImage image, final List<CustomLabel> labels,
			final Map<String, Color> COLORS) {
		// System.out.println(image.getWidth() + ", " + image.getHeight());

		final Graphics2D g = image.createGraphics();

		for (final var label : labels) {
			g.setColor(COLORS.get(label.name()));

			final var box = label.geometry().boundingBox();

			int x = (int) (box.left() * image.getWidth());
			int y = (int) (box.top() * image.getHeight());

			final int width = (int) (box.width() * image.getWidth());
			final int height = (int) (box.height() * image.getHeight());

			// System.out.println(label.name() + "\t (" + x + ", " + y + ")\t" + width + " "
			// + height);
			g.drawRect(x, y, width, height);

			if (x > image.getWidth()) {
				x = image.getWidth() - 20;
			} else {
				x += 10;
			}

			if (y > image.getHeight()) {
				y = image.getHeight() - 20;
			}

			g.drawString(label.confidence() + "", x, y);
		}

		final ImageIcon icon = new ImageIcon(image);
		label.setIcon(icon);

		frame.setSize(image.getWidth(), image.getHeight());

	}

}
