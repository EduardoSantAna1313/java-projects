/*
 * COPYRIGHT...
 */
package br.com.edu.java.proxy.service.impl;

import java.util.List;

import br.com.edu.java.proxy.db.dao.annotations.InjectDAO;
import br.com.edu.java.proxy.db.dao.impl.UserDAO;
import br.com.edu.java.proxy.model.User;
import br.com.edu.java.proxy.service.GenericService;

/**
 * Class to UserService.
 *
 * @author Eduardo
 */
public class UserService extends GenericService {

	@InjectDAO
	public UserDAO userDAO;

	public List<User> listAll() {
		return userDAO.listAll();
	}

	public User get(final Integer recordId) {
		return userDAO.findById(recordId);
	}

	public User get(final String value) {
		return userDAO.findByValue(value);
	}

	public String getName(final int id) {
		return userDAO.getNameById(id);
	}

	public List<String> listNames() {
		return userDAO.listNames();
	}

}
