package dicegame.test;

import dicegame.framework.Dice;
import dicegame.framework.DiceCollectionImpl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link Dice}
 */
public class DiceTest {

	private Dice de1;
	private Dice de2;

	@Before
	public void setUp(){
		de1 = new Dice(6);
		de2 = new Dice(6);
	}

	@Test
	public void deSuperieurTest(){
		de1.setFaceValue(4);
		de2.setFaceValue(5);
		assertTrue(de1.compareTo(de2)==1);
	}

	@Test
	public void deInferieurTest(){
		de1.setFaceValue(4);
		de2.setFaceValue(5);
		assertTrue(de2.compareTo(de1)==-1);
	}

	@Test
	public void memeDeTest(){
		de1.setFaceValue(4);
		assertTrue(de1.compareTo(de1)==0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void deNullTest(){
		de1.setFaceValue(4);
		de1.compareTo(null);
	}
}
