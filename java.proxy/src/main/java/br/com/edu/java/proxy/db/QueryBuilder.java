/*
 * COPYRIGHT...
 */
package br.com.edu.java.proxy.db;

import java.lang.reflect.Method;

import br.com.edu.java.proxy.db.dao.annotations.SqlDAO;

/**
 * QueryBuilder.
 *
 * @author Eduardo
 */
class QueryBuilder {

	/**
	 * New instance of QueryBuilder
	 */
	private QueryBuilder() {
		// NA
	}

	/**
	 * Create the sql from method.
	 *
	 * @param method
	 * @param tableName
	 *
	 * @return
	 */
	public static String sql(final Method method, final String tableName) {

		// get the full sql from annotation
		final SqlDAO sqldao = method.getAnnotation(SqlDAO.class);

		if (sqldao != null) {
			return sqldao.value();
		}

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ").append(tableName);

		sql.append(" WHERE 1 = 1 ");

		if (method.getName().startsWith("listAll")) {
			// BLA
		} else if (method.getName().startsWith("findById")) {
			sql.append(" AND ").append(tableName + "ID = ? ");
		} else if (method.getName().startsWith("findByValue")) {
			sql.append(" AND ").append(" Value = ? ");
		}

		return sql.toString();
	}

}
