/*
 * COPYRIGHT...
 */
package design.patterns.estructural.adapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import design.patterns.estructural.adapter.core.hexagonal.entity.User;
import design.patterns.estructural.adapter.core.hexagonal.repository.UserRepository;

/**
 * Adapter to userrepository.
 *
 * @author Eduardo
 */
public class UserRepoMemoryAdapter implements UserRepository {

	private final Set<User> users = new HashSet<>();

	@Override
	public void save(final User user) {
		users.add(user);
	}

	@Override
	public List<User> listAll() {
		return new ArrayList<>(users);
	}

}
