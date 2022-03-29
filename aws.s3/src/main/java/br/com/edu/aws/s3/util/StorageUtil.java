package br.com.edu.aws.s3.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

import br.com.edu.aws.s3.storage.StorageObject;

/**
 * Utility class to storage.
 *
 * @author Eduardo
 */
public class StorageUtil {

	/**
	 * New instance of StorageUtil
	 */
	private StorageUtil() {
		// NA
	}

	public static String getTestProperty(final String key) {
		final var props = load("test.properties");

		return props.getProperty(key);
	}

	public static Properties load(final String fileName) {
		final Properties prop = new Properties();

		try (var is = new FileInputStream(Path.of("src/main/resources").resolve(fileName).toFile())) {
			prop.load(is);
		} catch (final IOException error) {
			// NA
		}

		return prop;
	}

	public static Path save(final StorageObject obj, final Path dir) throws IOException {

		final Path output = dir.resolve(obj.getKey());
		final File parent = output.toFile().getParentFile();

		if (!parent.exists()) {
			parent.mkdirs();
		}

		try (final FileOutputStream fileOutputStream = new FileOutputStream(output.toFile());
				final BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);) {

			final byte[] b = new byte[10240];
			int r = 0;

			while ((r = obj.getIs().read(b, 0, b.length)) != -1) {
				bos.write(b, 0, r);
			}

			return output;
		}

	}

}
