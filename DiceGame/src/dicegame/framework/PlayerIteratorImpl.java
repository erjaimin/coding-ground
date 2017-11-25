package dicegame.framework;

/**
 * This is an implementation class of {@link PlayerIterator}
 */
public class PlayerIteratorImpl implements PlayerIterator {

	/**
	 * data members
	 */
	private PlayerCollectionImpl playerCollection;
	private int playerIndex;
	
	/**
	 * create an instance of {@link PlayerCollectionImpl}
	 * @param diceCollection
	 */
	public PlayerIteratorImpl(PlayerCollectionImpl playerCollection) {
		this.playerCollection = playerCollection;
	}

	/* (non-Javadoc)
	 * @see dicegame.framework.PlayerIterator#nextPlayer()
	 */
	@Override
	public Player nextPlayer() {
        Player player = playerCollection.getPlayerArray()[playerIndex];
        playerIndex++;
        return player;
	}

	/* (non-Javadoc)
	 * @see dicegame.framework.PlayerIterator#isLastPlayer()
	 */
	@Override
	public boolean isLastPlayer() {
		if(playerIndex < playerCollection.getPlayerArray().length){
			return false;
		}
		return true;
	}

}
