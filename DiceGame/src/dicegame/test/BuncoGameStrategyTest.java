package dicegame.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import dicegame.bunco.BuncoGame;
import dicegame.bunco.BuncoGameStrategy;
import dicegame.framework.DiceIterator;
import dicegame.framework.Game;
import dicegame.framework.GameConstants;
import dicegame.framework.GameObjectFactory;
import dicegame.framework.GameStrategy;
import dicegame.framework.PlayerCollection;
import dicegame.framework.PlayerIterator;
/**
 * Test class for {@link BuncoGameStrategy}
 */
public class BuncoGameStrategyTest {

	private GameStrategy gameStrategy;
	private Game game;
	private GameObjectFactory gameFactory;
	
	@Before
	public void setUp() throws Exception {
		gameFactory = new GameObjectFactory();
		gameStrategy = gameFactory.getGameStrategy(GameConstants.BUNCO_VERSION_CODE);
		game = new BuncoGame(1, 3, 3, 6, gameStrategy);
		game.startGame();
	}

	@Test
	public void testCalculateScore1() {
		DiceIterator diceIterator = game.getDiceCollection().getDiceIterator();
		while(!diceIterator.isLastDice()){
			diceIterator.nextDice().setFaceValue(3);
		}
		((BuncoGame)game).setCurrentRound(3);
		
		int calculateScore = gameStrategy.calculateScoreTurn(game);
		assertEquals(GameConstants.BUNCO_POINTS, calculateScore);
	}
	
	@Test
	public void testCalculateScore2() {
		DiceIterator diceIterator = game.getDiceCollection().getDiceIterator();
		while(!diceIterator.isLastDice()){
			diceIterator.nextDice().setFaceValue(3);
		}
		((BuncoGame)game).setCurrentRound(2);
		
		int calculateScore = gameStrategy.calculateScoreTurn(game);
		assertEquals(GameConstants.NO_BUNCO_POINTS, calculateScore);
	}
	
	@Test
	public void testCalculateScore3() {
		DiceIterator diceIterator = game.getDiceCollection().getDiceIterator();
		int i = 1;
		while(!diceIterator.isLastDice()){
			diceIterator.nextDice().setFaceValue(i++);
		}
		((BuncoGame)game).setCurrentRound(2);
		
		int calculateScore = gameStrategy.calculateScoreTurn(game);
		assertEquals(1, calculateScore);
	}
	
	@Test
	public void testCalculateScore4() {
		DiceIterator diceIterator = game.getDiceCollection().getDiceIterator();
		int i = 1;
		while(!diceIterator.isLastDice()){
			diceIterator.nextDice().setFaceValue(i++);
		}
		((BuncoGame)game).setCurrentRound(5);
		
		int calculateScore = gameStrategy.calculateScoreTurn(game);
		assertEquals(0, calculateScore);
	}

	@Test
	public void testCalculateWinner() {
		PlayerIterator playerIterator = game.getPlayerCollection().getPlayerIterator();
		int i = 1;
		while(!playerIterator.isLastPlayer()){
			playerIterator.nextPlayer().setScore(i++);
		}
		PlayerCollection calculateWinner = gameStrategy.calculateWinner(game);
		PlayerIterator sortedPlayerIterator = calculateWinner.getPlayerIterator();
		assertEquals(3, sortedPlayerIterator.nextPlayer().getScore());
		assertEquals(2, sortedPlayerIterator.nextPlayer().getScore());
		assertEquals(1, sortedPlayerIterator.nextPlayer().getScore());
	}
}
