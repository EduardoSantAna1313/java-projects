package br.com.edu.calculo.parcelas;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.Executors;

import br.com.edu.calculo.parcelas.util.ConnectionUtil;

/**
 * Calculo de parcelas concorrentes.
 *
 * @author Eduardo
 */
public class MainDeleteCalculoParcelas {

	/**
	 * int - MAX.
	 */
	private static final int MAX = 48;

	public static void main(final String[] args) throws Exception {

		try (final var conn = ConnectionUtil.loadConnection();) {
			conn.setAutoCommit(true);

			init(conn);

			System.out.println("calc....");
			final var service = Executors.newFixedThreadPool(10);
			for (var i = 0; i < MAX / 2; i++) {
				service.submit(new Api(conn, i));
			}
			service.shutdown();

			do {
				// NA
			} while (!service.isTerminated());

			System.out.println("***************************");
			System.out.println("listando as parcelas");
			System.out.println("***************************");
			listParcelas(conn);
		}
	}

	/**
	 * Init...
	 *
	 * @param conn
	 */
	public static void init(final Connection conn) {
		PreparedStatement pstmt = null;
		try {
			final var sql = Files.readString(Path.of("src/main/resouces/delete.sql"));

			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (final Exception error) {
			error.printStackTrace();
		} finally {
			ConnectionUtil.close(pstmt);
		}

		System.out.println("Deleted...");

		for (var i = 0; i < MAX; i++) {
			try {

				final var sql = Files.readString(Path.of("src/main/resouces/insert.sql"));

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i);
				pstmt.executeUpdate();
			} catch (final Exception error) {
				error.printStackTrace();
				throw new RuntimeException(error.getMessage());
			} finally {
				ConnectionUtil.close(pstmt);
			}
		}

		System.out.println("Inserted");
	}

	/**
	 * List parcelas.
	 *
	 * @param conn
	 */
	public static void listParcelas(final Connection conn) {
		try (final var pstmt = conn.prepareStatement("SELECT * FROM parcela order by id asc ");
				var rs = pstmt.executeQuery();) {
			while (rs.next()) {
				final var id = rs.getInt("id");
				final var valor = rs.getString("valor");
				System.out.println(id + " valor: " + valor);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

}
