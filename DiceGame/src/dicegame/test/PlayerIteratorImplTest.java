package dicegame.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import dicegame.framework.Player;
import dicegame.framework.PlayerCollectionImpl;
import dicegame.framework.PlayerIterator;
import dicegame.framework.PlayerIteratorImpl;

/**
 * Test class for {@link PlayerIteratorImpl}
 */
public class PlayerIteratorImplTest {

	private PlayerIterator playerIterator;
	private PlayerCollectionImpl playerCollection;
	
	@Before
	public void setUp() throws Exception {
		playerCollection = new PlayerCollectionImpl(5);
		playerIterator = new PlayerIteratorImpl(playerCollection);
	}

	@Test
	public void testPlayerIterator() {
		Player p1 = new Player(1, 3);
		Player p2 = new Player(2, 4);
		Player p3 = new Player(3, 0);
		Player p4 = new Player(4, 21);
		Player p5 = new Player(5, 5);
		playerCollection.addPlayer(p1);
		playerCollection.addPlayer(p2);
		playerCollection.addPlayer(p3);
		playerCollection.addPlayer(p4);
		playerCollection.addPlayer(p5);
		assertTrue(playerIterator.nextPlayer().equals(p1));
		assertTrue(playerIterator.nextPlayer().equals(p2));
		assertTrue(playerIterator.nextPlayer().equals(p3));
		assertTrue(playerIterator.nextPlayer().equals(p4));
		assertTrue(playerIterator.nextPlayer().equals(p5));
		assertTrue(playerIterator.isLastPlayer	());
		
		//test sorting
		playerCollection.sortPlayers();
		playerIterator = new PlayerIteratorImpl(playerCollection);
		assertTrue(playerIterator.nextPlayer().equals(p4));
		assertTrue(playerIterator.nextPlayer().equals(p5));
		assertTrue(playerIterator.nextPlayer().equals(p2));
		assertTrue(playerIterator.nextPlayer().equals(p1));
		assertTrue(playerIterator.nextPlayer().equals(p3));
	}

}
