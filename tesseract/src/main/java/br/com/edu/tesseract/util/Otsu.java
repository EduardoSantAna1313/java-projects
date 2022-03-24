package br.com.edu.tesseract.util;

/**
 * Class to Otsu Threshold.
 *
 * @author Eduardo
 */
public class Otsu {

	private static final int RADIX = 256;

	private int threshold;

	public int getThreshold() {
		return threshold;
	}

	public Otsu(final int[][] pixels) {
		threshold(pixels);
	}

	protected void threshold(final int[][] pixels) {
		// create a histogram out of the pixels
		final int[] n_t = histogram(pixels);

		// get sum of all pixel intensities
		final int sum = sumIntensities(n_t);

		// perform Otsu's method
		calcThreshold(n_t, pixels.length * pixels[0].length, sum);
	}

	private int[] histogram(final int[][] pixels) {
		final int[] n_t = new int[RADIX];

		for (int i = 0; i < pixels.length; i++) {

			for (int j = 0; j < pixels[0].length; j++) {
				n_t[pixels[i][j]]++;
			}

		}

		return n_t;
	}

	private int sumIntensities(final int[] n_t) {
		int sum = 0;

		for (int i = 0; i < n_t.length; i++) {
			sum += i * n_t[i];
		}

		return sum;
	}

	private void calcThreshold(final int[] array, final int N, final int sum) {
		double variance;
		double bestVariance = Double.NEGATIVE_INFINITY;

		double meanBg = 0;
		double weightBg = 0;

		double meanFg = (double) sum / (double) N; // mean of population
		double weightFg = N; // weight of population

		double diffMeans;

		// loop through all candidate thresholds
		int t = 0;

		while (t < RADIX) {
			// calculate variance
			diffMeans = meanFg - meanBg;
			variance = weightBg * weightFg * diffMeans * diffMeans;

			// store best threshold
			if (variance > bestVariance) {
				bestVariance = variance;
				threshold = t;
			}

			// go to next candidate threshold
			while (t < RADIX && array[t] == 0) {
				t++;
			}

			if (t >= RADIX) {
				break;
			}

			meanBg = (meanBg * weightBg + array[t] * t) / (weightBg + array[t]);
			meanFg = (meanFg * weightFg - array[t] * t) / (weightFg - array[t]);
			weightBg += array[t];
			weightFg -= array[t];
			t++;
		}

	}

}