/*
 * COPYRIGHT...
 */
package br.com.edu.java.proxy.db.dao;

import java.util.List;
import java.util.Properties;

/**
 * Abstract DAO.
 *
 * @author Eduardo
 */
public interface AbstractDAO<T extends PO> {

	T saveEx(Properties ctx, T po, String trxName);

	T updateEx(Properties ctx, T po, String trxName);

	void delete(Properties ctx, T po, String trxName);

	T findById(Integer id);

	T findByValue(String value);

	List<T> listAll();

}
