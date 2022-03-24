package br.com.edu.prisoners.dilemma.strategy;

import java.util.List;

import br.com.edu.prisoners.dilemma.player.Player;
import br.com.edu.prisoners.dilemma.type.StrategyType;

/**
 * @author Eduardo
 */
public abstract class Strategy {

	public abstract StrategyType play(List<StrategyType> pPreviousMatches);

	public abstract StrategyType firstPlay();

	public StrategyType getLastStrategy(final List<StrategyType> pListTypes) {
		return pListTypes.get(pListTypes.size() - 1);
	}

	public static void calculate(final Player pPlayer1, final Player pPlayer2) {

		final StrategyType p1 = pPlayer1.play(pPlayer2.getMatches());
		final StrategyType p2 = pPlayer2.play(pPlayer1.getMatches());

		if (StrategyType.BETRAY.equals(p1) && StrategyType.BETRAY.equals(p2)) {
			pPlayer1.minusScore(1);
			pPlayer2.minusScore(1);
		}

		if (StrategyType.COOPERATE.equals(p1) && StrategyType.COOPERATE.equals(p2)) {
			pPlayer1.addScore(3);
			pPlayer2.addScore(3);
		}

		if (StrategyType.BETRAY.equals(p1) && StrategyType.COOPERATE.equals(p2)) {
			pPlayer1.addScore(5);
			pPlayer2.minusScore(1);
		}

		if (StrategyType.COOPERATE.equals(p1) && StrategyType.BETRAY.equals(p2)) {
			pPlayer1.minusScore(1);
			pPlayer2.addScore(5);
		}

	}

}
