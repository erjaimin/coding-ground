package dicegame.framework;

import java.util.Arrays;

/**
 * This is an implementation class of {@link PlayerCollection}
 */

public class PlayerCollectionImpl implements PlayerCollection {

	/**
	 * data members
	 */
	private Player[] playerArray;
	private int playerIndex;
	
	/**
	 * create an instance of {@link PlayerCollectionImpl}
	 * @param noOfDices
	 */
	public PlayerCollectionImpl(int noOfPlayers) {
		playerArray = new Player[noOfPlayers];
	}
	
	/* (non-Javadoc)
	 * @see dicegame.framework.PlayerCollection#addPlayer(Player player)
	 */
	@Override
	public void addPlayer(Player player) {
		if(playerIndex < playerArray.length){
			playerArray[playerIndex] = player;
			playerIndex++;
		}
	}

	/* (non-Javadoc)
	 * @see dicegame.framework.PlayerCollection#getPlayerIterator()
	 */
	@Override
	public PlayerIterator getPlayerIterator() {
		return new PlayerIteratorImpl(this);
	}

	/* (non-Javadoc)
	 * @see dicegame.framework.PlayerCollection#sortPlayers()
	 */
	@Override
	public void sortPlayers() {
		Arrays.sort(playerArray);
	}
	
	/**
	 * @return the playerArray
	 */
	public Player[] getPlayerArray() {
		return playerArray;
	}

}
