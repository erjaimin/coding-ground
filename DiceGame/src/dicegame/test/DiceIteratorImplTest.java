package dicegame.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dicegame.framework.Dice;
import dicegame.framework.DiceCollectionImpl;
import dicegame.framework.DiceIterator;
import dicegame.framework.DiceIteratorImpl;

/**
 * Test class for {@link DiceIteratorImpl}
 */
public class DiceIteratorImplTest {

	private DiceIterator diceIterator;
	private DiceCollectionImpl diceCollection;
	
	@Before
	public void setUp(){
		diceCollection = new DiceCollectionImpl(2);
		diceIterator = new DiceIteratorImpl(diceCollection);
	}
		
	@Test
	public void testDiceIterator() {
		Dice d1 = new Dice(4);
		Dice d2 = new Dice(6);
		diceCollection.addDice(d1);
		diceCollection.addDice(d2);
		assertTrue(diceIterator.nextDice().equals(d1));
		assertTrue(diceIterator.nextDice().equals(d2));
		assertTrue(diceIterator.isLastDice());
	}
}
