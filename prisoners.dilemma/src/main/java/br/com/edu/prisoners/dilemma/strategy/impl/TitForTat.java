package br.com.edu.prisoners.dilemma.strategy.impl;

import java.util.List;

import br.com.edu.prisoners.dilemma.strategy.Strategy;
import br.com.edu.prisoners.dilemma.type.StrategyType;

/**
 * @author Eduardo
 */
public class TitForTat extends Strategy {

	@Override
	public StrategyType play(final List<StrategyType> pPreviousOpponents) {

		return getLastStrategy(pPreviousOpponents);
	}

	@Override
	public StrategyType firstPlay() {
		return StrategyType.COOPERATE;
	}

}
