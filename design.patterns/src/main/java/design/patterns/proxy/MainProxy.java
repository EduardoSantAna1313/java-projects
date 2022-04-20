package design.patterns.proxy;

/**
 * Class to Test the proxy pattern.
 *
 * @author Eduardo
 */
public class MainProxy {

	public static void main(final String[] args) {
		final BankOperations bank = new Bank();
		bank.deposit(123L, 500L);
		bank.withdraw(456L, "8888", 50L);
		bank.withdraw(456L, "9999", 50L);
		bank.withdraw(456L, "1234", 200L);

		bank.changePassord(123L, "1111", "4321");
		bank.changePassord(123L, "1234", "4321");

		bank.withdraw(123L, "4321", 700L);

		System.out.println("---------------------------------------");
		System.out.println("---------------------ATM---------------");
		System.out.println("---------------------------------------");

		final BankOperations atm = new Atm(bank);
		atm.deposit(456L, 700L);
		atm.withdraw(456L, "4567", 400);
		atm.withdraw(456L, "4567", 50L);

		atm.changePassord(456L, "4567", "1234");
	}

}
