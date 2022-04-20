/*
 * COPYRIGHT...
 */
package design.patterns.proxy;

/**
 * Class to .
 *
 * @author Eduardo
 */
class Atm implements BankOperations {

	private final BankOperations bank;

	public Atm(final BankOperations bank) {
		super();
		this.bank = bank;
	}

	@Override
	public void deposit(final long account, final long amount) {
		System.out.println("ATM proxy sending request to bank");
		bank.deposit(account, amount);
	}

	@Override
	public void withdraw(final long account, final String password, final long amount) {

		if (amount > 300) {
			System.out.println("You may not withdraw amounts over 300");
			return;
		}

		System.out.println("ATM proxy sending request to bank");

		bank.withdraw(account, password, amount);

	}

	@Override
	public void changePassord(final long account, final String oldPassword, final String newPassword) {
		System.out.println("You cant perform this operation at an ATM!");
		return;

	}

}
