/*
 * COPYRIGHT...
 */
package design.patterns.flyweight;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import design.patterns.estructural.flyweight.FlyweightFactory;
import design.patterns.estructural.flyweight.MusicPlayerService;

/**
 * Class to Test the flyweight pattern.
 *
 * @author Eduardo
 */
public class MusicFlyweightTest {

	@Test
	public void testTotalMusicsInMemory() {

		final MusicPlayerService service = new MusicPlayerService();
		service.listenMusic("User A", "About a girl;Nirvana;171");
		service.listenMusic("User A", "About a girl;Nirvana;171");
		service.listenMusic("User A", "About a girl;Nirvana;171");
		service.listenMusic("User B", "Lithium;Nirvana;257");
		service.listenMusic("User B", "Lithium;Nirvana;257");
		service.listenMusic("User B", "About a girl;Nirvana;171");
		service.listenMusic("User C", "Lithium;Nirvana;257");
		service.listenMusic("User C", "About a girl;Nirvana;171");

		// There're only 2 different songs
		assertEquals(2, FlyweightFactory.getTotalMusics());
	}

}
