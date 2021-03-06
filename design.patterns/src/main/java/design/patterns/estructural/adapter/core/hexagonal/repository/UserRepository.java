/*
 * COPYRIGHT...
 */
package design.patterns.estructural.adapter.core.hexagonal.repository;

import java.util.List;

import design.patterns.estructural.adapter.core.hexagonal.entity.User;

/**
 * Class to UserRepository.
 *
 * @author Eduardo
 */
public interface UserRepository {

	void save(final User user);

	List<User> listAll();

}
