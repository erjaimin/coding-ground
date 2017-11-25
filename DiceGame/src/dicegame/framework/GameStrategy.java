package dicegame.framework;

public interface GameStrategy {
	
	/**
	 * This method calculate the player score based on the rolled dices
	 * based on the game score rules
	 * 1. check for bunco, if bunco then return bunco score
	 * 2. check for same face value for all dices, then return non-bunco score
	 * 3. check for individual dice match with current round, then assign one point for each match
	 * @param game
	 * @return
	 */
	int calculateScoreTurn(Game game);
	
	/**
	 * This method sorts the player collection based on score in descending order
	 * @param game
	 * @return sorted player collection
	 */
	PlayerCollection calculateWinner(Game game);
}
