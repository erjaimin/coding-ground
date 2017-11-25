package dicegame.test;

import org.junit.Test;

import dicegame.bunco.BuncoGameStrategy;
import dicegame.framework.Dice;
import dicegame.framework.DiceCollection;
import dicegame.framework.DiceCollectionImpl;
import dicegame.framework.DiceIterator;

/**
 * Test class for {@link DiceCollectionImpl}
 */
public class DiceCollectionImplTest {

	@Test
	public void testDiceIterator() {
		Dice d1 = new Dice(1);
		Dice d2 = new Dice(2);
		Dice d3 = new Dice(3);
		Dice d4 = new Dice(4);
		
		DiceCollection diceCollection = new DiceCollectionImpl(4);
		diceCollection.addDice(d1);
		diceCollection.addDice(d2);
		diceCollection.addDice(d3);
		diceCollection.addDice(d4);
		
		System.out.println("-----Dice list-----");
        printDices(diceCollection);
	}

	private void printDices(DiceCollection diceCollection) {
		DiceIterator diceIterator = diceCollection.getDiceIterator();
		while(!diceIterator.isLastDice()){
			Dice nextDice = diceIterator.nextDice();
			System.out.println(nextDice);
		}
	}

}
