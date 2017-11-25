package dicegame.framework;

/**
 * This is an abstract class represent a dice game steps and
 * provides methods for set up 
 *
 */
public abstract class Game{
	
	/**
	 * data members
	 */
	private int noOfTurns;
	private int noOfDices;
	private int noOfPlayers;
	private int noOfDiceFaces;
	private static GameObjectFactory gameObjectFactory = new GameObjectFactory();
	protected PlayerCollection playerCollection;
	protected DiceCollection diceCollection;
	private GameStrategy gameStrategy;
	
	/**
	 * creates an insatnce of {@link Game}
	 * @param noOfTurns
	 * @param noOfDices
	 * @param noOfPlayers
	 * @param noOfDiceFaces
	 * @param gameStrategy
	 */
	public Game(int noOfTurns, int noOfDices, int noOfPlayers, int noOfDiceFaces, GameStrategy gameStrategy) {
		this.noOfTurns = noOfTurns;
		this.noOfDices = noOfDices;
		this.noOfPlayers = noOfPlayers;
		this.noOfDiceFaces = noOfDiceFaces;
		this.gameStrategy = gameStrategy;
	}
	
	/**
	 * steps involved in a dice game
	 */
	public void startGame(){
		createPlayers();
		createDices();
		playGame();
	}

	/**
	 * dice game version specific implentations
	 */
	protected abstract void createPlayers();
    protected abstract void createDices();
    protected abstract void playGame();

	/**
	 * @return the noOfTurns
	 */
	public int getNoOfTurns() {
		return noOfTurns;
	}

	/**
	 * @return the noOfDices
	 */
	public int getNoOfDices() {
		return noOfDices;
	}

	/**
	 * @return the noOfPlayers
	 */
	public int getNoOfPlayers() {
		return noOfPlayers;
	}

	/**
	 * @return the noOfDiceFaces
	 */
	public int getNoOfDiceFaces() {
		return noOfDiceFaces;
	}

	/**
	 * @return the gameObjectFactory
	 */
	public GameObjectFactory getGameObjectFactory() {
		return gameObjectFactory;
	}

	/**
	 * @return the gameStrategy
	 */
	public GameStrategy getGameStrategy() {
		return gameStrategy;
	}

	/**
	 * @return the playerCollection
	 */
	public PlayerCollection getPlayerCollection() {
		return playerCollection;
	}

	/**
	 * @return the diceCollection
	 */
	public DiceCollection getDiceCollection() {
		return diceCollection;
	}
}
