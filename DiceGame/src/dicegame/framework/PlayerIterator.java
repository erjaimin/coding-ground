package dicegame.framework;

/**
 *  This interface provides method to iterate {@link PlayerCollection}
 */
public interface PlayerIterator {
	
	/**
	 * Gets next {@link Player} in {@link PlayerCollection}
	 * @return
	 */
	Player nextPlayer();
	
	/**
	 * Check if the the current Player is the last one in {@link PlayerCollection}
	 * @return true if last player, otherwise false
	 */
	boolean isLastPlayer();
}
