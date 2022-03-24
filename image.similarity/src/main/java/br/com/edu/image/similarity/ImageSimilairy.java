/*
 * COPYRIGHT...
 */
package br.com.edu.image.similarity;

import java.awt.image.BufferedImage;

/**
 * @author Eduardo
 */
public class ImageSimilairy {

	private static final int NEW_WIDTH = 9;

	private static final int NEW_HEIGTH = 8;

	public double calculate(final BufferedImage image1, final BufferedImage image2) {

		final BufferedImage image1GS = ImageUtil.toGrayScale(image1);
		final BufferedImage resize1 = ImageUtil.resize(image1GS, NEW_WIDTH, NEW_HEIGTH);

		final int[] brigth1 = toBrigthArray(resize1);
		final int[] aHash1 = calculateHash(brigth1);

		final BufferedImage image2GS = ImageUtil.toGrayScale(image2);
		final BufferedImage resize2 = ImageUtil.resize(image2GS, NEW_WIDTH, NEW_HEIGTH);

		final int[] brigth2 = toBrigthArray(resize2);
		final int[] aHash2 = calculateHash(brigth2);

		return calculateDiff(aHash1, aHash2);
	}

	private static double calculateDiff(final int[] aHash1, final int[] aHash2) {

		double erro = 0;

		for (int i = 0; i < aHash1.length; i++) {

			if (aHash1[i] != aHash2[i]) {
				erro++;
			}

		}

		return erro / aHash1.length;
	}

	private static int[] calculateHash(final int[] brigth1) {
		final int[] buffer = new int[brigth1.length - 1];

		for (int i = 0; i < brigth1.length - 1; i++) {
			buffer[i] = brigth1[i] - brigth1[i + 1];
		}

		return buffer;
	}

	public static int[] toBrigthArray(final BufferedImage pImage) {
		final int[] input = new int[pImage.getWidth() * pImage.getHeight()];

		for (int postX = 0; postX < pImage.getWidth(); postX++) {
			final int pos = postX * pImage.getHeight();

			for (int postY = 0; postY < pImage.getHeight(); postY++) {

				final int rgb = pImage.getRGB(postX, postY);

				final int red = (rgb & 0xff0000) >> 16;
				final int green = (rgb & 0xff00) >> 8;
				final int blue = rgb & 0xff;

				final double bright = 0.2126 * red + 0.7152 * green + 0.0722 * blue;

				input[pos + postY] = (int) bright;
			}

		}

		return input;
	}

	public static String toBitArray(final int input) {
		final String str = Integer.toBinaryString(input);

		final StringBuilder s = new StringBuilder();

		for (int i = 0; i < 32 - str.length(); i++) {
			s.append("0");
		}

		s.append(str);
		return s.toString();

	}

}
