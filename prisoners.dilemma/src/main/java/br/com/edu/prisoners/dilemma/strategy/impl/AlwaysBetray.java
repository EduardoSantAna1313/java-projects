package br.com.edu.prisoners.dilemma.strategy.impl;

import java.util.List;

import br.com.edu.prisoners.dilemma.strategy.Strategy;
import br.com.edu.prisoners.dilemma.type.StrategyType;

/**
 * @author Eduardo
 */
public class AlwaysBetray extends Strategy {

	@Override
	public StrategyType play(final List<StrategyType> pPreviousMatches) {
		return StrategyType.BETRAY;
	}

	@Override
	public StrategyType firstPlay() {
		return StrategyType.BETRAY;
	}

}
