/*
 * COPYRIGHT...
 */
package br.com.edu.java.proxy.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import br.com.edu.java.proxy.db.QueryDAO;
import br.com.edu.java.proxy.db.dao.annotations.InjectDAO;

/**
 * Class to GenericService.
 *
 * @author Eduardo
 */
public class GenericService {

	/**
	 * New instance of GenericService
	 */
	public GenericService() {
		super();
		injectProperties();
	}

	/**
	 * Inject properties.
	 */
	private void injectProperties() {

		for (final var field : this.getClass().getDeclaredFields()) {

			final InjectDAO annotation = field.getAnnotation(InjectDAO.class);

			if (annotation == null) {
				continue;
			}

			setFieldInstance(field);

		}

	}

	/**
	 * Set the field with the proxy.
	 *
	 * @param field
	 */
	private void setFieldInstance(final Field field) {
		final var instance = Proxy.newProxyInstance(GenericService.class.getClassLoader(), new Class[] {
			field.getType()
		}, handler(field.getType()));

		field.setAccessible(true);

		try {
			field.set(this, instance);
		} catch (final Exception error) {
			error.printStackTrace();
		}

	}

	/**
	 * Proxy handler.
	 *
	 * @param clazz
	 *
	 * @return
	 */
	private static InvocationHandler handler(final Class<?> clazz) {

		return (proxy, method, methodArgs) -> {

			final QueryDAO query = new QueryDAO();

			return query.execute(clazz, method, methodArgs);

		};
	}

}
