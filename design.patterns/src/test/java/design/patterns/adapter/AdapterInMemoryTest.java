/*
 * COPYRIGHT...
 */
package design.patterns.adapter;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import design.patterns.estructural.adapter.UserRepoMemoryAdapter;
import design.patterns.estructural.adapter.core.hexagonal.business.UserBusiness;
import design.patterns.estructural.adapter.core.hexagonal.entity.User;
import design.patterns.estructural.adapter.core.hexagonal.repository.UserRepository;

/**
 * Class to Test MemoryAdapter repository.
 *
 * @author Eduardo
 */
public class AdapterInMemoryTest {

	private UserRepository repository;

	private UserBusiness business;

	@Before
	public void before() {
		// use the in memory adapter
		repository = new UserRepoMemoryAdapter();
		business = new UserBusiness(repository);
	}

	@Test(expected = IllegalStateException.class)
	public void testNameNull() {
		business.save(new User(null, "bla@ble.com", "123"));
	}

	@Test(expected = IllegalStateException.class)
	public void testEmailNull() {
		business.save(new User("bla", null, "123"));
	}

	@Test(expected = IllegalStateException.class)
	public void testPwdNull() {
		business.save(new User("bla", "bla@ble.com", null));
	}

	@Test
	public void testSave() {
		business.save(new User("bla", "bla@ble.com", "123"));
		assertTrue(true);
	}

	@Test
	public void testList() {
		final User user = new User("bla", "bla@ble.com", "123");
		business.save(user);

		final var list = business.listAll();
		assertFalse(list.isEmpty());

		final User saved = list.get(0);

		assertEquals(saved, user);
		assertEquals(saved.getName(), user.getName());
		assertEquals(saved.getEmail(), user.getEmail());
		assertEquals(saved.getPassword(), user.getPassword());
	}

	@Test
	public void testDistinctList() {
		business.save(new User("bla", "bla@ble.com", "123"));
		business.save(new User("bla", "bla@ble.com", "123"));

		final var list = business.listAll();

		assertEquals(1, list.size());
	}

}
