package dicegame.bunco;

import static dicegame.framework.GameConstants.BUNCO_POINTS;
import java.util.Random;
import dicegame.framework.Dice;
import dicegame.framework.DiceIterator;
import dicegame.framework.Game;
import dicegame.framework.GameStrategy;
import dicegame.framework.Player;
import dicegame.framework.PlayerCollection;
import dicegame.framework.PlayerIterator;
/**
 * This class represents the dice game version called bunco+
 *
 */
public class BuncoGame extends Game {
	
	/**
	 * data members
	 */
	private static Random rand = new Random();
	private int currentRound;
	private int currentPlayer;
	
	/**
	 * 
	 * @param noOfTurns
	 * @param noOfDices
	 * @param noOfPlayers
	 */
	public BuncoGame(int noOfTurns, int noOfDices, int noOfPlayers, int noOfDiceFaces, GameStrategy gameStrategy) {
		super(noOfTurns, noOfDices, noOfPlayers, noOfDiceFaces, gameStrategy);
	}

	/**
	 * this method creates players and adds it to player collection
	 */
	@Override
	protected void createPlayers() {
		playerCollection = getGameObjectFactory().getPlayerCollection(getNoOfPlayers());
		for(int i=0; i<getNoOfPlayers(); i++){
			playerCollection.addPlayer(getGameObjectFactory().getPlayerObject(i+1));
		}	
	}

	/**
	 * this method creates dices and adds it to dice collection
	 */
	@Override
	protected void createDices() {
		diceCollection = getGameObjectFactory().getDiceCollection(getNoOfDices());
		for(int i=0; i<getNoOfDices(); i++){
			diceCollection.addDice(getGameObjectFactory().getDiceObject());
		}
	}

	/**
	 * start game play for given no of turns and players
	 */
	@Override
	protected void playGame() {
		for(int lap=0; lap < getNoOfTurns(); lap++){
			currentRound = lap + 1;
			System.out.println("=============================================================");
			System.out.println("Round # "+ currentRound +" played by "+getNoOfPlayers()+ " players...");
			System.out.println("=============================================================");
			// calculate player score and decide winner
			for(int player=0; player< getNoOfPlayers(); player++){
				currentPlayer = player + 1;
				int calculateScoreTurn = calculateScoreTurn();
				updatePlayerScore(calculateScoreTurn);
				System.out.println("-------------------------------------------------------------");
			}
			int winnerId = calculateWinner();
			if(winnerId != 0){
				System.out.println("Thus, the player # "+winnerId+" wins round # "+currentRound);
			}else{
				System.out.println("It's tie, so nobody wins round # "+currentRound);
			}
		}
	}
	
	/**
	 * updates player score after the players turn is finished
	 * @param playerScore
	 */
	private void updatePlayerScore(int playerScore) {
		PlayerIterator playerIterator = playerCollection.getPlayerIterator();
		while(!playerIterator.isLastPlayer()){
			Player nextPlayer = playerIterator.nextPlayer();
			if(nextPlayer.getId() == currentPlayer){
				nextPlayer.setScore(playerScore);
				break;
			}
		}
	}

	/**
	 * this method rolls dices and display face values
	 */
	private void rollDices() {
		StringBuilder sb = new StringBuilder();
		DiceIterator diceIterator = diceCollection.getDiceIterator();
		while(!diceIterator.isLastDice()){
			Dice dice = diceIterator.nextDice();
			dice.setFaceValue(rand.nextInt(getNoOfDiceFaces())+1);
			sb.append(dice.getFaceValue()).append(" ");
		}
		System.out.println("He gets [ "+sb.toString()+"]");
	}

	/**
	 * After the player rolls the dices, the method calculates the player score
	 * based on game strategy rules, player continues to play if valid score
	 * @return total score by player in a turn
	 */
	public int calculateScoreTurn() {
		boolean isScored = false;
		int playerScore = 0;
		do {
			System.out.print("Player # " + currentPlayer + " rolls the dice: ");
			rollDices();
			int score = getGameStrategy().calculateScoreTurn(this);
			playerScore += score;
			isScored = (score > 0 && score != BUNCO_POINTS);
			System.out.println("Points scored: " + score);
		} while (isScored);
		System.out.println("A total of " + playerScore + 
				" point(s) are scored for player # " + currentPlayer + " in this round.");
		return playerScore;
	}

	/**
	 * This method uses game strategy to get the sorted player collection
	 * based on scores and return player id with max score 
	 * @return winner player id
	 */
	public int calculateWinner() {
		PlayerCollection calculateWinner = getGameStrategy().calculateWinner(this);
		int winnerId = 0;
		PlayerIterator playerIterator = calculateWinner.getPlayerIterator();
		if(!playerIterator.isLastPlayer()){
			Player player = playerIterator.nextPlayer();
			winnerId = player.getId();
			if(!playerIterator.isLastPlayer() && player.compareTo(playerIterator.nextPlayer()) == 0){
				winnerId = 0;
			}
		}
		return winnerId;
	}

	/**
	 * @return the currentRound
	 */
	public int getCurrentRound() {
		return currentRound;
	}
	
	/**
	 *  only for test purpose
	 *  sets the currentRound
	 */
	public void setCurrentRound(int currentRound) {
		this.currentRound = currentRound;
	}

	/**
	 * @return the currentPlayer
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}

}
