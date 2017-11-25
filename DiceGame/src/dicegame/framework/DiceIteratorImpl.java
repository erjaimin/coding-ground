package dicegame.framework;

/**
 * This is an implementation class of {@link DiceIterator}
 */
public class DiceIteratorImpl implements DiceIterator {
	
	/**
	 * data members
	 */
	private DiceCollectionImpl diceCollection;
	private int diceIndex;
	
	/**
	 * create an instance of {@link DiceIteratorImpl}
	 * @param diceCollection
	 */
	public DiceIteratorImpl(DiceCollectionImpl diceCollection) {
		this.diceCollection = diceCollection;
	}
	
	/* (non-Javadoc)
	 * @see dicegame.framework.DiceIterator#nextDice()
	 */
	@Override
	public Dice nextDice() {
        Dice dice = diceCollection.getDiceArray()[diceIndex];
        diceIndex++;
        return dice;
	}

	/* (non-Javadoc)
	 * @see dicegame.framework.DiceIterator#isLastDice()
	 */
	@Override
	public boolean isLastDice() {
		if(diceIndex < diceCollection.getDiceArray().length){
			return false;
		}
		return true;
	}

}
