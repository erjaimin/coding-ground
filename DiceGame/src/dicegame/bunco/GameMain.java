package dicegame.bunco;

import static dicegame.framework.GameConstants.BUNCO_VERSION_CODE;
import dicegame.framework.Game;
import dicegame.framework.GameObjectFactory;
import dicegame.framework.GameStrategy;

/**
 * Game Main class
 *
 */
public class GameMain {

	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		// create game object factory, game strategy & game instance
		GameObjectFactory objectFactory = new GameObjectFactory();
		GameStrategy gameStrategy = objectFactory.getGameStrategy(BUNCO_VERSION_CODE);
		Game gameInstance = objectFactory.getGameInstance(BUNCO_VERSION_CODE, 5, 3, 5, 6);
		gameInstance.startGame();
	}

}
