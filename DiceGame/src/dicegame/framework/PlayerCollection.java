package dicegame.framework;

import org.junit.experimental.theories.Theories;

/**
 * This interface is a collection of Player objects and perform
 * related operations
 */
public interface PlayerCollection {
	
	/**
	 * Adds player to the collection
	 * @param player to be added
	 */
	void addPlayer(Player player);
	
	/**
	 * Returns an iterator for {@link PlayerCollection}
	 * @return {@link PlayerIterator}
	 */
	PlayerIterator getPlayerIterator();
	
	/**
	 * Sorts {@link PlayerCollection}
	 */
	void sortPlayers();
}
