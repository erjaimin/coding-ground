package dicegame.framework;

/**
 * This interface is a collection of Dice objects and perform
 * related operations
 */
public interface DiceCollection {
	
	/**
	 * Adds dice to the collection
	 * @param dice to be added
	 */
	void addDice(Dice dice);
	
	/**
	 * Returns an iterator for {@link DiceCollection}
	 * @return {@link DiceIterator}
	 */
	DiceIterator getDiceIterator();
}
