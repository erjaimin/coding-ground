package dicegame.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import dicegame.bunco.BuncoGame;
import dicegame.bunco.BuncoGameStrategy;
import dicegame.framework.Game;

/**
 * Test class for {@link Game}
 */
public class GameTest {
	private Game gameInstance;
	
	@Before
	public void setUp(){
		gameInstance = new BuncoGame(1, 3, 3, 6, new BuncoGameStrategy());
	}
	
	@Test
	public void testInitializeGame(){
		gameInstance.startGame();
		assertNotNull(gameInstance.getDiceCollection());
		assertNotNull(gameInstance.getPlayerCollection());
		assertEquals(1,gameInstance.getNoOfTurns());
		assertEquals(3,gameInstance.getNoOfDices());
		assertEquals(3,gameInstance.getNoOfPlayers());
		assertEquals(6, gameInstance.getNoOfDiceFaces());
	}
}
