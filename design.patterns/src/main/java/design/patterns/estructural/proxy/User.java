/*
 * COPYRIGHT...
 */
package design.patterns.estructural.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class to .
 *
 * @author Eduardo
 */
@Data
@AllArgsConstructor
class User {

	private String name;

	private long accountNumber;

	private String password;

	private long balance;

}