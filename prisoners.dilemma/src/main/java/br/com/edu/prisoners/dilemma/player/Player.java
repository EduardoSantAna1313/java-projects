package br.com.edu.prisoners.dilemma.player;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.prisoners.dilemma.strategy.Strategy;
import br.com.edu.prisoners.dilemma.type.StrategyType;

/**
 * @author Eduardo
 */
public class Player {

	private static final int LOSE_SCORE = -5;

	private static final int DRAW_SCORE = 1;

	private static final int WIN_SCORE = 2;

	private final String name;

	private int score;

	private final Strategy startegy;

	private final List<StrategyType> matches;

	/**
	 * Create a new instance of Player
	 *
	 * @param startegy
	 */
	public Player(final String name, final Strategy startegy) {
		super();
		this.name = name;
		this.startegy = startegy;
		this.score = 0;
		matches = new ArrayList<>();
	}

	/**
	 * Retrieve the value of name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieve the value of score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Add value to score.
	 *
	 * @param score
	 *                  the score to add
	 */
	public void addScore(final int score) {
		this.score += score;
	}

	/**
	 * Minus value to score.
	 *
	 * @param score
	 *                  the score to minus
	 */
	public void minusScore(final int score) {
		this.score -= score;
	}

	/**
	 * Retrieve the value of startegy.
	 *
	 * @return the startegy
	 */
	public Strategy getStartegy() {
		return startegy;
	}

	/**
	 * Retrieve the value of matches.
	 *
	 * @return the matches
	 */
	public List<StrategyType> getMatches() {
		return new ArrayList<>(matches);
	}

	public StrategyType play(final List<StrategyType> pOpponentsMatches) {

		if (pOpponentsMatches.isEmpty()) {
			return getStartegy().firstPlay();
		}

		final StrategyType play = getStartegy().play(pOpponentsMatches);
		this.matches.add(play);
		return play;
	}

	public static List<Player> createPlayers(final List<Strategy> pStrategies) {
		final List<Player> players = new ArrayList<>();

		for (final Strategy strategy : pStrategies) {

			for (int i = 0; i < 2; i++) {
				final Player p = new Player(strategy.getClass().getSimpleName() + i, strategy);
				players.add(p);
			}

		}

		return players;
	}

	public void fight(final Player other) {
		final StrategyType p1 = this.play(other.getMatches());
		final StrategyType p2 = other.play(this.getMatches());

		if (StrategyType.BETRAY.equals(p1) && StrategyType.BETRAY.equals(p2)) {
			this.minusScore(LOSE_SCORE);
			other.minusScore(LOSE_SCORE);
		}

		if (StrategyType.COOPERATE.equals(p1) && StrategyType.COOPERATE.equals(p2)) {
			this.addScore(DRAW_SCORE);
			other.addScore(DRAW_SCORE);
		}

		if (StrategyType.BETRAY.equals(p1) && StrategyType.COOPERATE.equals(p2)) {
			this.addScore(WIN_SCORE);
			other.minusScore(LOSE_SCORE);
		}

		if (StrategyType.COOPERATE.equals(p1) && StrategyType.BETRAY.equals(p2)) {
			this.minusScore(LOSE_SCORE);
			other.addScore(WIN_SCORE);
		}

		// System.out.println(this.getName() + " " + this.getScore() + "\tx\t" +
		// other.getName() + " " + other.getScore());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (name == null ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		final Player other = (Player) obj;

		if (name == null) {

			if (other.name != null) {
				return false;
			}

		} else if (!name.equals(other.name)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return this.getName() + "\tscore: " + this.score;
	}

}
