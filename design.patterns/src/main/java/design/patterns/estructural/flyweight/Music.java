/*
 * COPYRIGHT...
 */
package design.patterns.estructural.flyweight;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class to Music.
 *
 * @author Eduardo
 */
@Data
@AllArgsConstructor
public class Music {

	// the unmodifiable music
	private final MusicFlyweight musicFlywight;

	private int playQty;

	public Music(final MusicFlyweight musicFlywight) {
		super();
		this.musicFlywight = musicFlywight;
	}

	public void listening() {
		playQty++;
	}

	public void print() {
		System.out.println(getMusicFlywight() + " " + getPlayQty() + " times");
	}

	public int calculateAllDuration() {
		return getPlayQty() * getMusicFlywight().getDurationInSeconds();
	}

}
