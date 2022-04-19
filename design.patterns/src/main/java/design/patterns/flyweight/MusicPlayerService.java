/*
 * COPYRIGHT...
 */
package design.patterns.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to MusicPlayerService.
 *
 * @author Eduardo
 */
public class MusicPlayerService {

	private final Map<String, Map<String, Music>> songsByUser;

	public MusicPlayerService() {
		songsByUser = new HashMap<>();
	}

	public void listenMusic(final String user, final String desc) {
		final Map<String, Music> userPlaylist = songsByUser.computeIfAbsent(user, a -> new HashMap<>());

		final Music song = userPlaylist.computeIfAbsent(desc,
				a -> new Music(FlyweightFactory.getInstance().getMusic(desc)));

		System.out.println(user + " is listenning " + song.getMusicFlywight().getName());
		song.listening();

	}

	public void report() {

		songsByUser.keySet().forEach(user -> {
			System.out.println("---------------------------------");
			System.out.println(user + " has listen...");

			final int timeCounter = songsByUser.get(user).values().stream()
					// print
					.peek(Music::print)
					// to int
					.mapToInt(Music::calculateAllDuration).sum();

			System.out.println(user + " has listen music for " + timeCounter + " seconds");

		});

		System.out.println("\n\n");
		System.out.println("---------------------------------");
		System.out.println("Total of musics in flyWeight's memory: " + FlyweightFactory.getTotalMusics());

	}

}
