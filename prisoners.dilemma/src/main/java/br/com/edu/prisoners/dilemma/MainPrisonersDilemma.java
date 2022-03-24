package br.com.edu.prisoners.dilemma;

import java.util.List;

import br.com.edu.prisoners.dilemma.player.Player;
import br.com.edu.prisoners.dilemma.strategy.Strategy;
import br.com.edu.prisoners.dilemma.util.ClassUtil;

/**
 * @author Eduardo
 */
public class MainPrisonersDilemma {

	public static void main(final String[] args) throws Exception {
		final List<Strategy> strategies = ClassUtil.listStrategies();

		final List<Player> players = Player.createPlayers(strategies);

		for (int r = 0; r < 1; r++) {

			for (final Player p1 : players) {

				for (final Player p2 : players) {

					if (p1.equals(p2)) {
						continue;
					}

					p1.fight(p2);
				}

			}

		}

		players.forEach(p -> System.out.println(p));
	}

}
