/*
 * COPYRIGHT...
 */
package design.patterns.adapter;

import design.patterns.adapter.core.hexagonal.business.UserBusiness;
import design.patterns.adapter.core.hexagonal.entity.User;
import design.patterns.adapter.core.hexagonal.repository.UserRepository;

/**
 * Adapter example.
 *
 * @author Eduardo
 */
public class MainAdapter {

	public static void main(final String[] args) {

		final UserRepository repository = new UserRepoFileSystemAdapter();

		final UserBusiness business = new UserBusiness(repository);

		business.save(new User("bla", "bla@ble.com", "123"));
		business.save(new User("ble", "bla@ble.com", "123"));
		business.save(new User("blu", "bla@ble.com", "123"));

		System.out.println("List users: ");
		business.listAll().forEach(System.out::println);
	}

}
