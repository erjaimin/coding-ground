package dicegame.framework;

import static dicegame.framework.GameConstants.BUNCO_VERSION_CODE;
import dicegame.bunco.BuncoGame;
import dicegame.bunco.BuncoGameStrategy;
/**
 * This class is an game object factory that creates instances 
 * required 
 */
public class GameObjectFactory {
	
	/**
	 * creates empty dice object
	 * @return {@link Dice}
	 */
	public Dice getDiceObject(){
		return new Dice();
	}
	
	/**
	 * creates dice object with given faceValue
	 * @param faceValue
	 * @return {@link Dice}
	 */
	public Dice getDiceObject(int faceValue){
		return new Dice(faceValue);
	}
	
	/**
	 * creates player object with given playerId
	 * @param playerId
	 * @return {@link Player}
	 */
	public Player getPlayerObject(int playerId){
		return new Player(playerId);
	}
	
	/**
	 * creates a game instance with provides inputs
	 * @param gameVersion
	 * @param noOfTurns
	 * @param noOfDices
	 * @param noOfPlayers
	 * @param noOfDiceFaces
	 * @return {@link Game}
	 */
	public Game getGameInstance(String gameVersion, int noOfTurns, int noOfDices, int noOfPlayers, int noOfDiceFaces){
		if(gameVersion.equals(BUNCO_VERSION_CODE)){
			return new BuncoGame(noOfTurns, noOfDices, noOfPlayers, noOfDiceFaces, getGameStrategy(BUNCO_VERSION_CODE));
		}
		return null;
	}
	
	/**
	 * create a dice collection with given noOfDices
	 * @param noOfDices
	 * @return {@link DiceCollection}
	 */
	public DiceCollection getDiceCollection(int noOfDices){
		return new DiceCollectionImpl(noOfDices);
	}
	
	/**
	 * create a player collection with given noOfPlayers
	 * @param noOfPlayers
	 * @return {@link PlayerCollection}
	 */
	public PlayerCollection getPlayerCollection(int noOfPlayers){
		return new PlayerCollectionImpl(noOfPlayers);
	}
	
	/**
	 * Creates Game strategy based on game version code
	 * @param gameVersion code
	 * @return {@link GameStrategy}
	 */
	public GameStrategy getGameStrategy(String gameVersion){
		if(gameVersion.equals(BUNCO_VERSION_CODE)){
			return new BuncoGameStrategy();
		}
		return null;
	}
}
