package br.com.edu.image.similarity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Eduardo
 */
public class MainTestSimilarity {

	public static void main(final String[] args) throws IOException {

		final BufferedImage image1 = ImageIO.read(new File("resources/img2.jpg"));

		final BufferedImage image2 = ImageIO.read(new File("resources/img3.jpg"));

		ImageUtil.show(image1, image2);

		final double diff = new ImageSimilairy().calculate(image1, image2);

		System.out.println("Diff: " + diff * 100 + "%");

	}

}
