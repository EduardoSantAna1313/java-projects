package br.com.edu.java.proxy.db;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

import br.com.edu.java.proxy.db.dao.PO;
import br.com.edu.java.proxy.model.User;

/**
 * Class to QueryDAO.
 *
 * @author Eduardo
 */
public class QueryDAO {

	/**
	 * New instance of Query
	 *
	 * @param ctx
	 * @param tableName
	 * @param trxName
	 */
	public QueryDAO() {
		super();
	}

	/**
	 * Excute and return the value.
	 *
	 * @param clazz
	 * @param method
	 * @param parameters
	 *
	 * @return
	 */
	public Object execute(final Class<?> clazz, final Method method, final Object[] parameters) {

		try {

			final Class<? extends PO> clazzModel = getPOClass(clazz);

			final String tableName = getTableName(clazzModel);

			final var sql = QueryBuilder.sql(method, tableName);

			System.out.println("SQL: '" + sql + "' with params: " + Arrays.toString(parameters));

			if (method.getName().equals("listAll")) {
				return List.of(new User(1, "user1"), new User(2, "user2"));
			} else if (method.getName().equals("findById")) {
				return new User(1, "user1");
			} else if (method.getName().equals("findByValue")) {
				return new User(2, "user2");
			} else if (method.getName().equals("getNameById") || method.getName().equals("listNames")) {
				return null;
			} else {
				throw new UnsupportedOperationException("Unsupported method: " + method.getName());
			}

		} catch (final Exception error) {
			error.printStackTrace();
		}

		return null;

	}

	/**
	 * Get the table from name from the class.
	 *
	 * @param clazzModel
	 *
	 * @return
	 */
	private String getTableName(final Class<? extends PO> clazzModel) {

		try {
			final var field = clazzModel.getField("tableName");

			return (String) field.get(null);

		} catch (final Exception error) {
			error.printStackTrace();
		}

		return null;
	}

	/**
	 * Get the PO class.
	 *
	 * @param clazz
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Class<? extends PO> getPOClass(final Class<?> clazz) {

		try {
			final ParameterizedType type = (ParameterizedType) clazz.getGenericInterfaces()[0];

			final Type typeArg = type.getActualTypeArguments()[0];

			return (Class<? extends PO>) Class.forName(typeArg.getTypeName());
		} catch (final Exception error) {
			return null;
		}

	}

	/**
	 * Get the context from parameters.
	 *
	 * @param parameters
	 *
	 * @return
	 */
	protected static Properties getContext(final Object[] parameters) {

		if (parameters == null || parameters.length == 0) {
			return new Properties();
		}

		return (Properties) Stream.of(parameters).filter(Properties.class::isInstance).findFirst()
				.orElse(new Properties());
	}

}
