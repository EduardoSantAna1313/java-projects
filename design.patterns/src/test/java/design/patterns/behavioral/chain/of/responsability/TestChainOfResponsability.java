/*
 * COPYRIGHT...
 */
package design.patterns.behavioral.chain.of.responsability;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import design.patterns.behavioral.chain.of.responsability.bill.Dispenser;

/**
 * Class to TestChainOfResponsability.
 *
 * @author Eduardo
 */
public class TestChainOfResponsability {

	@Test
	public void testDecimal() {
		final Dispenser dispenser = Dispenser.defaultDispenser();

		final var result = dispenser.withdraw(13);

		// 1 x 10 dollar bill
		assertEquals(1, result.get(10));
		// 3 x 1 dollar bill
		assertEquals(3, result.get(1));

		assertNull(result.get(5));
		assertNull(result.get(20));
		assertNull(result.get(100));
		assertNull(result.get(200));
	}

	@Test
	public void testBinary() {
		final Dispenser dispenser = Dispenser.binaryBillDispenser();
		final var result = dispenser.withdraw(1024);

		assertEquals(4, result.get(256));
		assertNull(result.get(128));
		assertNull(result.get(64));
		assertNull(result.get(32));
		assertNull(result.get(16));
		assertNull(result.get(8));
		assertNull(result.get(4));
		assertNull(result.get(2));
		assertNull(result.get(1));
	}

}
