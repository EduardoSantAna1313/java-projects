/*
 * COPYRIGHT...
 */
package design.patterns.behavioral.chain.of.responsability.bill;

/**
 * Class to MainChainOfResponsability.
 *
 * @author Eduardo
 */
class MainBillChainOfResponsability {

	public static void main(final String[] args) {
		final Dispenser dispenser = Dispenser.defaultDispenser();

		dispenser.withdraw(267).forEach((k, v) -> System.out.println(v + " x $" + k + " bill"));
	}

}
