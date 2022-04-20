package design.patterns.behavioral.chain.of.responsability.bill;

import java.util.Map;

/**
 * Class to Dispenser.
 *
 * @author Eduardo
 */
public class Dispenser {

	private final Bill init;

	private Dispenser() {
		init = new Bill(100, new Bill(50, new Bill(20, new Bill(10, new Bill(5, new Bill(1))))));
	}

	private Dispenser(final Bill... bills) {
		init = bills[0];

		for (int i = 1; i < bills.length; i++) {
			init.setNext(bills[i]);
		}

	}

	public static Dispenser defaultDispenser() {
		return new Dispenser();
	}

	public static Dispenser binaryBillDispenser() {
		return new Dispenser(new Bill(256), new Bill(128), new Bill(64), new Bill(32), new Bill(16), new Bill(8),
				new Bill(4), new Bill(2), new Bill(1));
	}

	public Map<Integer, Integer> withdraw(final int value) {
		return init.process(value);
	}

}
