/*
 * COPYRIGHT...
 */
package br.com.edu.java.proxy.db.dao.impl;

import java.util.List;

import br.com.edu.java.proxy.db.dao.AbstractDAO;
import br.com.edu.java.proxy.db.dao.annotations.SqlDAO;
import br.com.edu.java.proxy.model.User;

/**
 * UserDAO.
 *
 * @author Eduardo
 */
public interface UserDAO extends AbstractDAO<User> {

	@SqlDAO("Select Name FROM User WHERE ID = ? ")
	String getNameById(Integer id);

	@SqlDAO("Select Name FROM User ")
	List<String> listNames();

}
