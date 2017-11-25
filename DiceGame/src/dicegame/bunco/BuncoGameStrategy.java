package dicegame.bunco;

import static dicegame.framework.GameConstants.BUNCO_POINTS;
import static dicegame.framework.GameConstants.NO_BUNCO_POINTS;
import dicegame.framework.Dice;
import dicegame.framework.DiceCollection;
import dicegame.framework.DiceIterator;
import dicegame.framework.Game;
import dicegame.framework.GameObjectFactory;
import dicegame.framework.GameStrategy;
import dicegame.framework.PlayerCollection;
/**
 * This class implements game score calculation logic and
 * logic to calculate winner 
 *
 */
public class BuncoGameStrategy implements GameStrategy {

	/**
	 * 
	 * @return calculated player score
	 */
	@Override
	public int calculateScoreTurn(Game game) {
		if (game instanceof BuncoGame) {
			BuncoGame buncoGame = (BuncoGame) game;
			DiceCollection diceCollection = game.getDiceCollection();
			GameObjectFactory gameObjectFactory = buncoGame.getGameObjectFactory();
			int currentRound = buncoGame.getCurrentRound();
			Dice diceObject = gameObjectFactory.getDiceObject(currentRound);
			if (checkForBunco(diceObject, diceCollection)) {
				return BUNCO_POINTS;
			} else if (checkForSameFaceValues(diceCollection)) {
				return NO_BUNCO_POINTS;
			} else {
				int score = 0;
				DiceIterator diceIterator = game.getDiceCollection().getDiceIterator();
				while (!diceIterator.isLastDice()) {
					if (diceIterator.nextDice().compareTo(diceObject) == 0) {
						score++;
					}
				}
				return score;
			}
		}
		return 0;
	}
	
	/**
	 * This method checks if the player rolled the same number for all dices as the
	 * round number to determine its bunco or not
	 * @param diceObject
	 * @param diceCollection
	 * @return true if Bunco otherwise returns false
	 */
	private boolean checkForBunco(Dice diceObject, DiceCollection diceCollection) {
		DiceIterator diceIterator = diceCollection.getDiceIterator();
		while(!diceIterator.isLastDice()){
			if(diceIterator.nextDice().compareTo(diceObject) != 0){
				return false;
			}
		}	
		return true;
	}

	/**
	 * This method checks if the player rolled the same number for all dices but the number
	 * isn't round number.
	 * @param diceCollection
	 * @return true if all dice face values are same, otherwise false
	 */
	private boolean checkForSameFaceValues(DiceCollection diceCollection) {
		DiceIterator diceIterator = diceCollection.getDiceIterator();
		if(!diceIterator.isLastDice()){
			Dice dice = diceIterator.nextDice();
			while (!diceIterator.isLastDice()) {
				if(diceIterator.nextDice().compareTo(dice) != 0){
					return false;
				}
			}
		}else{
			return false;
		}
		return true;
	}

	/**
	 * returns sorted player collection
	 */
	@Override
	public PlayerCollection calculateWinner(Game game) {
		PlayerCollection playerCollection = game.getPlayerCollection();
		playerCollection.sortPlayers();
		return playerCollection;
	}

}
