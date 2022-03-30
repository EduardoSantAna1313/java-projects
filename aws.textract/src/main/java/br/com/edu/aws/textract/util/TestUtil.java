package br.com.edu.aws.textract.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Class to Test.
 *
 * @author Eduardo
 */
public class TestUtil {

	/**
	 * New instance of TestUtil
	 */
	private TestUtil() {
		// NA
	}

	/**
	 * Get test property.
	 *
	 * @param key
	 *
	 * @return
	 */
	public static String getTestProperty(final String key) {
		final var props = load("test.properties");

		return props.getProperty(key);
	}

	/**
	 * Load properties.
	 *
	 * @param fileName
	 *
	 * @return
	 */
	private static Properties load(final String fileName) {
		final Properties prop = new Properties();

		try (var is = new FileInputStream(Path.of("src/main/resources").resolve(fileName).toFile())) {
			prop.load(is);
		} catch (final IOException error) {
			// NA
			error.printStackTrace();
		}

		return prop;
	}

}
