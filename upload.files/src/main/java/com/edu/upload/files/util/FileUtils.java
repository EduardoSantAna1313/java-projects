package com.edu.upload.files.util;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.function.DoubleFunction;

/**
 * Class to FileUtil.
 *
 * @author Eduardo
 */
public class FileUtils {

	private FileUtils() {
		// NA
	}

	public static void save(final InputStream is, final Path output) throws Exception {

		int r = 0;
		final byte[] bytes = new byte[1024 * 1024];

		if (!output.getParent().toFile().exists()) {
			output.getParent().toFile().mkdirs();
		}

		long totalDownloaded = 0;

		try (final BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(output.toFile()));) {

			while ((r = is.read(bytes, 0, bytes.length)) != -1) {
				out.write(bytes, 0, r);

				totalDownloaded += r;
				System.out.println("Downloaded: " + format(totalDownloaded));
			}

		}

	}

	private static final long KB = 1024;

	private static final long MB = KB * 1024;

	private static final long GB = MB * 1024;

	public static String format(final long contentLength) {

		final DoubleFunction<String> formatter = a -> String.format("%.2f", a);

		if (contentLength < KB) {

			return String.valueOf(contentLength).concat(" B");

		} else if (contentLength <= MB) {

			final double value = (double) contentLength / KB;
			return formatter.apply(value).concat(" KB");

		} else if (contentLength <= GB) {

			final double value = (double) contentLength / MB;
			return formatter.apply(value).concat(" MB");

		} else {

			final double value = (double) contentLength / GB;
			return formatter.apply(value).concat(" GB");

		}

	}

}
