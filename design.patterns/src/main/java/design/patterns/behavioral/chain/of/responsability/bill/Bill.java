/*
 * COPYRIGHT...
 */
package design.patterns.behavioral.chain.of.responsability.bill;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class to .
 *
 * @author Eduardo
 */
class Bill implements Process {

	private final Map<Integer, Integer> map;

	private final int value;

	private Bill next;

	public Bill(final int value) {
		super();
		this.value = value;

		map = new TreeMap<>(Comparator.reverseOrder());
	}

	public Bill(final int value, final Bill next) {
		this(value);
		this.next = next;
	}

	public void setNext(final Bill next) {
		this.next = next;
	}

	@Override
	public Map<Integer, Integer> process(final int amount) {

		final int qty = amount / value;

		if (qty >= 1) {
			map.put(value, qty);
		}

		final int result = amount % value;

		if (next != null) {
			map.putAll(next.process(result));
		}

		return map;

	}

}
