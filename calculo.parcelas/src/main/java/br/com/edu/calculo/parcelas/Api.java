package br.com.edu.calculo.parcelas;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Eduardo
 */
class Api implements Runnable {

	private final Connection conn;

	private final int id;

	public Api(final Connection conn, final int id) {
		super();
		this.conn = conn;
		this.id = id;
	}

	@Override
	public void run() {
		updateAll(id);
	}

	public void updateAll(final int id) {

		PreparedStatement pstmt = null;
		try {
			final var sql = Files.readString(Path.of("src/main/resouces/update.sql"));

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, id);
			pstmt.setInt(3, id);
			pstmt.executeUpdate();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}