/*
 * COPYRIGHT...
 */
package design.patterns.proxy;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to .
 *
 * @author Eduardo
 */
class Bank implements BankOperations {

	private final Map<Long, User> userDatabase;

	public Bank() {
		userDatabase = new HashMap<>();
		userDatabase.put(123L, new User("User A", 123L, "1234", 10));
		userDatabase.put(456L, new User("User B", 456L, "4567", 0));
	}

	@Override
	public void deposit(final long account, final long amount) {
		final User user = userDatabase.get(account);

		if (user == null) {
			System.out.println("Invalid account!");
			return;
		}

		user.setBalance(user.getBalance() + amount);

		System.out.println(user.getName() + " " + amount + ". New balance: " + user.getBalance());
	}

	@Override
	public void withdraw(final long account, final String password, final long amount) {
		final User user = userDatabase.get(account);

		if (user == null) {
			System.out.println("Invalid account!");
			return;
		}

		if (!user.getPassword().equals(password)) {
			System.out.println("Wrong password!");
			return;
		}

		if (user.getBalance() < amount) {
			System.out.println("You dont have enough resources!");
			return;
		}

		user.setBalance(user.getBalance() - amount);

		System.out.println(user.getName() + " " + amount + ". New balance: " + user.getBalance());

	}

	@Override
	public void changePassord(final long account, final String oldPassword, final String newPassword) {
		final User user = userDatabase.get(account);

		if (user == null) {
			System.out.println("Invalid account!");
			return;
		}

		if (!user.getPassword().equals(oldPassword)) {
			System.out.println("Wrong password!");
			return;
		}

		user.setPassword(newPassword);

		System.out.println(user.getName() + "'s password updated successfully!");

	}

}
