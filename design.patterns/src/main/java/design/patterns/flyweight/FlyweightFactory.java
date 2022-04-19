/*
 * COPYRIGHT...
 */
package design.patterns.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to FlyweightFactory.
 *
 * @author Eduardo
 */
public class FlyweightFactory {

	private static final FlyweightFactory INSTANCE = new FlyweightFactory();

	private final Map<String, MusicFlyweight> cacheMusics;

	private FlyweightFactory() {
		super();
		cacheMusics = new HashMap<>();
	}

	public static FlyweightFactory getInstance() {
		return INSTANCE;
	}

	public MusicFlyweight getMusic(final String desc) {

		// add only new musics
		return cacheMusics.computeIfAbsent(desc, a -> getMusicByString(desc));
	}

	private static MusicFlyweight getMusicByString(final String desc) {
		final String[] musicData = desc.split(";");
		return new MusicFlyweight(musicData[0], musicData[1], Integer.valueOf(musicData[2]));
	}

	public static int getTotalMusics() {
		return getInstance().cacheMusics.size();
	}

}
