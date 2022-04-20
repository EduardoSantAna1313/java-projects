/*
 * COPYRIGHT...
 */
package design.patterns.estructural.flyweight;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class to MusicFlyweight.
 *
 * @author Eduardo
 */
@Data
@AllArgsConstructor
public class MusicFlyweight {

	private final String name;

	private final String artist;

	private final int durationInSeconds;

	@Override
	public String toString() {
		return getArtist() + "/" + getName();
	}

}
