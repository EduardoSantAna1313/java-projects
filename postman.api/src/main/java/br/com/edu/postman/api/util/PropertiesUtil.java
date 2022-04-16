/*
 * COPYRIGHT...
 */
package br.com.edu.postman.api.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class to Properties util.
 *
 * @author Eduardo
 */
public class PropertiesUtil {

	/**
	 * Properties - PROPERTIES.
	 */
	private static final Properties PROPERTIES = new Properties();

	static {

		try (final var is = new FileInputStream("src/main/resources/test.properties");) {
			PROPERTIES.load(is);
		} catch (final IOException error) {
			throw new RuntimeException(error.getMessage());
		}

	}

	/**
	 * New instance of PropertiesUtil
	 */
	private PropertiesUtil() {
		// NA
	}

	public static String getPropery(final String key) {
		return PROPERTIES.getProperty(key);
	}

}
