package design.patterns.estructural.flyweight;

/**
 * MainFlyweight.
 *
 * @author Eduardo
 */
public class MainFlyweight {

	public static void main(final String[] args) {

		final MusicPlayerService service = new MusicPlayerService();

		service.listenMusic("User A", "About a girl;Nirvana;171");
		service.listenMusic("User A", "About a girl;Nirvana;171");
		service.listenMusic("User A", "About a girl;Nirvana;171");
		service.listenMusic("User B", "Lithium;Nirvana;257");
		service.listenMusic("User B", "About a girl;Nirvana;171");

		service.report();
	}

}
