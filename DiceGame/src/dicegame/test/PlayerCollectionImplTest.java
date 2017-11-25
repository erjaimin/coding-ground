package dicegame.test;

import org.junit.Test;
import dicegame.framework.Player;
import dicegame.framework.PlayerCollection;
import dicegame.framework.PlayerCollectionImpl;
import dicegame.framework.PlayerIterator;

/**
 * Test class for {@link PlayerCollectionImpl}
 */
public class PlayerCollectionImplTest {

	@Test
	public void testDiceIterator() {
		Player p1 = new Player(1);
		Player p2 = new Player(2);
		Player p3 = new Player(3);
		Player p4 = new Player(4);
		Player p5 = new Player(5);
		
		PlayerCollection playerCollection = new PlayerCollectionImpl(5);
		playerCollection.addPlayer(p1);
		playerCollection.addPlayer(p2);
		playerCollection.addPlayer(p3);
		playerCollection.addPlayer(p4);
		playerCollection.addPlayer(p5);
		
		System.out.println("-----Player list-----");
        printPlayers(playerCollection);
        
        p1.setScore(2);
        p2.setScore(5);
        p3.setScore(3);
        p4.setScore(21);
        p5.setScore(4);
        
        System.out.println("-----Player list-----");
        printPlayers(playerCollection);
        
        playerCollection.sortPlayers();
        
        System.out.println("-----Player list sorted-----");
        printPlayers(playerCollection);
	}

	private void printPlayers(PlayerCollection playerCollection) {
		PlayerIterator playerIterator = playerCollection.getPlayerIterator();
		while(!playerIterator.isLastPlayer()){
			System.out.println(playerIterator.nextPlayer());
		}
	}

}
