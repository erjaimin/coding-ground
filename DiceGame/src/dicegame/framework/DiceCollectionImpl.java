package dicegame.framework;

/**
 * This is an implementation class of {@link DiceCollection}
 */
public class DiceCollectionImpl implements DiceCollection {
	
	/**
	 * data members
	 */
	private Dice[] diceArray;
	private int diceIndex;
	
	/**
	 * create an instance of {@link DiceCollectionImpl}
	 * @param noOfDices
	 */
	public DiceCollectionImpl(int noOfDices) {
		diceArray = new Dice[noOfDices];
	}
	
	/* (non-Javadoc)
	 * @see dicegame.framework.DiceCollection#addDice(Dice dice)
	 */
	@Override
	public void addDice(Dice dice) {
		if(diceIndex < diceArray.length){
			diceArray[diceIndex] = dice;
			diceIndex++;
		}
	}

	/* (non-Javadoc)
	 * @see dicegame.framework.DiceCollection#getDiceIterator()
	 */
	@Override
	public DiceIterator getDiceIterator() {
		return new DiceIteratorImpl(this);
	}

	/**
	 * @return the diceArray
	 */
	public Dice[] getDiceArray() {
		return diceArray;
	}

}
