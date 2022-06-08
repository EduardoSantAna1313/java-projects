package br.com.edu.java.proxy;

import br.com.edu.java.proxy.service.impl.UserService;

/**
 * Test with DaoProxy.
 *
 * @author Eduardo
 */
public class MainDaoProxy {

	public static void main(final String[] args) {

		final UserService service = new UserService();

		System.out.println("******List All******");

		final var list = service.listAll();

		for (final var user : list) {
			System.out.println(user);
		}

		System.out.println("\n******FindById******");

		System.out.println(service.get(123));

		System.out.println("\n******FindByValue******");

		System.out.println(service.get("NickName"));

		System.out.println("\n******GetNameById******");

		System.out.println(service.getName(123));

		System.out.println("\n******ListNames******");

		System.out.println(service.listNames());
	}

}
