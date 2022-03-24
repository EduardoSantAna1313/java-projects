package br.com.edu.tesseract.util;

public class Gaussian {

	private static final double SQRT2PI = Math.sqrt(2 * Math.PI);

	private final int[][] raw;

	public static final double GAUSSIAN_INTENSITY = 1.5;

	public static final int GAUSSIAN_RADIUS = 7;

	public Gaussian(final int[][] raw) {
		this.raw = raw;
	}

	public int[][] blur(final int rad, final double intens) {

		final int height = raw.length;

		final int width = raw[0].length;

		double norm = 0.;

		final double intensSquared2 = 2 * intens * intens;

		final double invIntensSqrPi = 1 / (SQRT2PI * intens);

		final double[] mask = new double[2 * rad + 1];

		final int[][] outGS = new int[height - 2 * rad][width - 2 * rad];

		// Create Gaussian kernel
		for (int x = -rad; x < rad + 1; x++) {
			final double exp = Math.exp(-(x * x / intensSquared2));

			mask[x + rad] = invIntensSqrPi * exp;
			norm += mask[x + rad];
		}

		// Convolve image with kernel horizontally
		for (int r = rad; r < height - rad; r++) {

			for (int c = rad; c < width - rad; c++) {
				double sum = 0.;

				for (int mr = -rad; mr < rad + 1; mr++) {
					sum += mask[mr + rad] * raw[r][c + mr];
				}

				// Normalize channel after blur
				sum /= norm;
				outGS[r - rad][c - rad] = (int) Math.round(sum);
			}

		}

		// Convolve image with kernel vertically
		for (int r = rad; r < height - rad; r++) {

			for (int c = rad; c < width - rad; c++) {
				double sum = 0.;

				for (int mr = -rad; mr < rad + 1; mr++) {
					sum += mask[mr + rad] * raw[r + mr][c];
				}

				// Normalize channel after blur
				sum /= norm;
				outGS[r - rad][c - rad] = (int) Math.round(sum);
			}

		}

		return outGS;
	}

}