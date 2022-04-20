
/*
 * COPYRIGHT...
 */
package design.patterns.proxy;

/**
 * Class to .
 *
 * @author Eduardo
 */
interface BankOperations {

	void deposit(long account, long amount);

	void withdraw(long account, String password, long amount);

	void changePassord(long account, String oldPassword, String newPassword);

}
