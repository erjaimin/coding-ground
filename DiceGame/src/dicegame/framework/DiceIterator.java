package dicegame.framework;

/**
 *  This interface provides method to iterate {@link DiceCollection}
 */
public interface DiceIterator {
	
	/**
	 * Gets next {@link Dice} in {@link DiceCollection}
	 * @return
	 */
	Dice nextDice();
	
	/**
	 * Check if the the current Dice is the last one in {@link DiceCollection}
	 * @return true if last dice, otherwise false
	 */
	boolean isLastDice();
}
