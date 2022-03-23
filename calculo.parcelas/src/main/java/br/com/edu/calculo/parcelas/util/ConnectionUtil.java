/*
 * COPYRIGHT...
 */
package br.com.edu.calculo.parcelas.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Connection Util.
 *
 * @author Eduardo
 */
public class ConnectionUtil {

	/**
	 * New instance of ConnectionUtil
	 */
	private ConnectionUtil() {
		// NA
	}

	public static Connection loadConnection() throws Exception {
		final var props = loadProperties();

		return DriverManager.getConnection(props.getProperty("url"), props);
	}

	/**
	 * Load properties.
	 *
	 * @return
	 *
	 * @throws IOException
	 */
	public static Properties loadProperties() throws IOException {
		try (var in = new FileInputStream("src/main/resouces/db.properties");) {
			final var props = new Properties();
			props.load(in);
			return props;
		}
	}

	/**
	 * Close the statement.
	 *
	 * @param pstmt
	 */
	public static void close(final PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
