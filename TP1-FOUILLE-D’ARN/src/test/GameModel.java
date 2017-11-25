package test;

import java.util.ArrayList;
import java.util.Random;

/**
 * The game model handles the logic of the game (generating the numbers, etc.).
 * A handle to the model can be used by the view-controller module
 * to trigger actions (for example, generate a new game) and retrieve information
 * about the current status of the game (the digits, the goal, etc.).
 *
 */
public class GameModel {
	// TODO Implement attributes and methods

	private String digit; //String of the combined digits of all the values in a game	

	private int goalSum = 0; //the goal value of the game

	private int numberOfGroup; //the number of groups in a game

	private int regroupCounter = 0; //the groups made by the user in the game

	private int sum; //the sum of the values chosen by the player (current sum)

	private int level = 1;

	private double arcadeScore = 0;

	private Random rand = new Random(); //random value

	private ArrayList<Integer> solutionArray = new ArrayList<>(); //arraylist that holds the game's solution by giving an assigned number for each group of number

	private ArrayList<Integer> numberHolder; //arraylist that holds the numbers generated for the game

	private ArrayList<Integer> selectionArray = new ArrayList<>(); //arraylist that store 0 if the tile hasn't been selected or a number for each selection

	private boolean allTilesChosen = false; //boolean that changes if all tiles have been selected

	private boolean sumGame = true; //boolean that changes if the game is looking for an average or a sum

	private boolean trainingMode = true;

	private boolean replayMode = false;

	private boolean arcadeMode = false;


	/**
	 * Void function that stores the digits generated for each game
	 */
	public void storeDigit() {

		String digit = ""; //instancing the digit

		int numberAssigned = 1; //number assigned for the solution array

		//for loop that goes on for all the number of groups generated
		for(int i = 0;i < getNumberOfGroup(); i++) {

			digit = digit + numberHolder.get(i); //the digit keeps on adding all the number generated

			//for loop that goes on for all the length of each number stored in the numberHolder arraylist
			for (int j = 0; j < Integer.toString(numberHolder.get(i)).length(); j++) {

				solutionArray.add(numberAssigned); //the solutionArray stores the number assigned for each values depending on the length of the number
			}

			numberAssigned++; //incrementing the numberAssigned
		}

		this.digit = digit; 

		selectionArray.clear(); //clearing the selection array

		//for loop that goes on till the length of the digit parameter
		for (int i = 0; i < digit.length(); i++) {

			selectionArray.add(0); //filling the selection array with 0's
		}
	}


	/**
	 * Void function that generates the group of numbers used in a game by generating random values between 3 and 6 for a normal game or
	 * even values between 3 and 6 in a game searching for an average
	 */
	public void generateGroup() {

		int randomNumber = rand.nextInt(4) + 3; //random number generating a value between 3 and 6

		numberOfGroup = randomNumber; //the number of groups

		if(arcadeMode == true){
			numberOfGroup = (int) (3 + Math.round(3.0 * level / 20)); 
		}
	}


	/**
	 * Getter that gets the value of each number generated for a normal game or an even number generated for a game that searches for an average
	 */
	public int getValue(){

		int randomValue = 0; //instance of the random value 

		int randomNumber = rand.nextInt(10); //random number generating a value between 0 and 9 that will be used for the probability of the appearance of a randomValue

		//if function that verifies if the random number is between 0 and 6 (probability of 0.7)
		if(randomNumber <= 6) {

			randomValue = rand.nextInt(9) + 1; //random value generating a value between 1 and 9

		}else if(randomNumber >= 7) {

			randomValue = rand.nextInt(90) + 10; //random value generating a value between 10 and 99
		}

		if(arcadeMode==true){

			double doubleDigitProba = 10*(0.3 + (0.3 * level / 20));

			if(randomNumber <= (9 - doubleDigitProba)) {

				randomValue = rand.nextInt(9) + 1; //random value generating a value between 1 and 9

			}else if(randomNumber >= (10 - doubleDigitProba)) {

				randomValue = rand.nextInt(90) + 10; //random value generating a value between 10 and 99
			}

		}
		goalSum += randomValue; //incrementing the goal by adding the random values

		return randomValue;

	}



	/**
	 * Void function that sets the value of clicked values in the selectionArray by taking the parameters:
	 * @param positionPressedInArray //the position pressed in the tile array
	 * @param positionReleasedInArray //the position released in the tile array
	 * @param pressedNumberAssigned //the number assigned for the pressed tile
	 * @param releasedNumberAssigned //the number assigned for the released tile
	 * @param myArray //the tile Array
	 */
	public void selectionStorer(int positionPressedInArray,int positionReleasedInArray,int pressedNumberAssigned, int releasedNumberAssigned, ArrayList<TilePanel> myArray){

		//if function that verifies if the pressed and released number are under 16
		if(pressedNumberAssigned < 16 && releasedNumberAssigned < 16) {

			selectionArray.set(positionPressedInArray, (pressedNumberAssigned + 1)); //sets the value of the pressed tile in the selectionArray

			selectionArray.set(positionReleasedInArray, (releasedNumberAssigned + 1)); //sets the value of the released tile in the selectionArray

		}else {

			selectionArray.set(positionPressedInArray, (positionPressedInArray+1)); //sets the value of the pressed tile in the selectionArray

			selectionArray.set(positionReleasedInArray, (positionPressedInArray+1)); //sets the value of the released tile in the selectionArray

		}

		++regroupCounter; //increments the number of regrouping
	}


	/**
	 * Void function that reset the SelectionArray by filling it with 0's
	 */
	public void resetSelectionArray() {

		//for loop that goes on till the length of the digits
		for (int i = 0; i < digit.length(); i++) {

			selectionArray.set(i, 0); //sets 0's in the selectionArray
		}
	}


	/**
	 * Void function that updates the sum buy adding the value of the tile clicked by taking in parameter:
	 * @param num // the number drawn on the tile
	 */
	public void updateSum(String num) {

		sum += Integer.parseInt(num); //sum increases by adding the integer of the string drawn on the tile
	}


	/**
	 * Void function that generates the game by calling and storing the numbers used in the game
	 */
	public void generateSumGame() {

		ArrayList<Integer> gameArray = new ArrayList<>(); //arraylist that stores the game numbers

		//for loop that goes on till the number of groups of number
		for(int i = 0; i < getNumberOfGroup(); i++) {

			gameArray.add(getValue()); //adds the value of the number in the gameArray
		}

		numberHolder = gameArray; //the arraylist numberHolder holds gameArray

		if(isSumGame() == false){

			if(goalSum%getNumberOfGroup()!=0){

				gameArray.clear();
				numberHolder.clear();
				goalSum = 0;
				generateSumGame();

			}

		}
	}

	/**
	 * Void function that calculates the score for the arcade mode by taking in parameter:
	 * @param noHelpSelection
	 * @param numberOfReset
	 */
	public void arcadeScoreCalculator(boolean noHelpSelection, int numberOfReset){

		arcadeScore = arcadeScore+ numberOfGroup*3 + digit.length()*2;
		if(sumGame==false){
			arcadeScore = arcadeScore + 20.0*(digit.length()/13);

		}
		if(noHelpSelection==true) {
			arcadeScore = arcadeScore + 6.0*(digit.length()/13);

		}

		arcadeScore = arcadeScore - 3*numberOfReset;

		if(arcadeScore < 0) {

			arcadeScore = 0;
		}
	}


	/**
	 * Getter of arcadeScore
	 * @return
	 */
	public double getArcadeScore() {
		return arcadeScore;
	}


	/**
	 * Setter of arcadeScore
	 * @param arcadeScore
	 */
	public void setArcadeScore(double arcadeScore) {
		this.arcadeScore = arcadeScore;
	}


	/**
	 * Getter of numberHolder
	 * @return
	 */
	public ArrayList<Integer> getNumberHolder() {
		return numberHolder;
	}


	/**
	 * Setter that sets the arraylist numberHolder by taking the parameter:
	 * @param numberHolder
	 */
	public void setNumberHolder(ArrayList<Integer> numberHolder) {
		this.numberHolder = numberHolder;
	}


	/**
	 * Setter that sets the string digit by taking the parameter:
	 * @param digit
	 */
	public void setDigit(String digit) {
		this.digit = digit;
	}


	/**
	 * Setter that sets the int numberOfGroup by taking the parameter:
	 * @param numberOfGroup 
	 */
	public void setNumberOfGroup(int numberOfGroup) {
		this.numberOfGroup = numberOfGroup;
	}


	/**
	 * Setter that sets the arraylist setSolutionArray by taking the parameter:
	 * @param solutionArray // An Arraylist
	 */
	public void setSolutionArray(ArrayList<Integer> solutionArray) {
		this.solutionArray = solutionArray;
	}


	/**
	 * Setter that sets the arraylist selectionArray by taking the parameter:
	 * @param selectionArray // An arraylist
	 */
	public void setSelectionArray(ArrayList<Integer> selectionArray) {
		this.selectionArray = selectionArray;
	}



	/**
	 * Getter of the boolean sumGame that returns sumGame
	 * @return
	 */
	public boolean isSumGame() {

		return sumGame;
	}




	/**
	 * Setter that sets the boolean sumGame by taking the parameter:
	 * @param sumGame //if it's a sum game or not
	 */
	public void setSumGame(boolean sumGame) {

		this.sumGame = sumGame;
	}

	/**
	 * Getter of the boolean allTilesChosen that return true or false
	 * @return
	 */
	public boolean isAllTilesChosen() {

		return allTilesChosen;
	}

	/**
	 * Setter of the boolean allTilesChosen that sets it and takes in parameter:
	 * @param allTilesChosen //true or false
	 */
	public void setAllTilesChosen(boolean allTilesChosen) {

		this.allTilesChosen = allTilesChosen;
	}

	/**
	 * @return
	 */
	public boolean isTrainingMode() {

		return trainingMode;
	}

	/**
	 * @param trainingMode
	 */
	public void setTrainingMode(boolean trainingMode) {

		this.trainingMode = trainingMode;
	}

	/**
	 * @return
	 */
	public boolean isReplayMode() {

		return replayMode;
	}

	/**
	 * @param replayMode
	 */
	public void setReplayMode(boolean replayMode) {

		this.replayMode = replayMode;
	}

	/**
	 * Getter of the boolean arcadeMode that returns a boolean
	 * @return
	 */
	public boolean isArcadeMode() {

		return arcadeMode;
	}

	/**
	 * @param arcadeMode
	 */
	public void setArcadeMode(boolean arcadeMode) {

		this.arcadeMode = arcadeMode;
	}

	/**
	 * Getter of the arrayList SolutionArray that returns the array
	 * @return
	 */
	public ArrayList<Integer> getSolutionArray() {

		return solutionArray;
	}

	/**
	 * Getter of the int regroupCounter the returns the int
	 * @return
	 */
	public int getRegroupCounter() {

		return regroupCounter;
	}

	/**
	 * Setter of the int regroupCounter that changes  it by taking in parameter:
	 * @param regroupCounter //the value wanted of regroupCounter
	 */
	public void setRegroupCounter(int regroupCounter) {

		this.regroupCounter = regroupCounter;
	}

	/**
	 * Getter of the selectionArray that returns the arraylist
	 * @return
	 */
	public ArrayList<Integer> getSelectionArray() {

		return selectionArray;
	}

	/**
	 * Getter of the sum that returns the int
	 * @return
	 */
	public int getSum() {

		return sum;
	} 

	/**
	 * Setter of the sum by taking in parameter:
	 * @param sum //the sum wanted
	 */
	public void setSum(int sum) {

		this.sum = sum;
	}

	/**
	 * Getter of goalSum that returns the int
	 * @return
	 */
	public int getGoalSum() {

		return goalSum;
	}

	/**
	 * Setter of goalSum that sets its value by taking in parameter:
	 * @param goalSum //the int wanted
	 */
	public void setGoalSum(int goalSum) {

		this.goalSum = goalSum;
	}

	/**
	 * Getter of digit that returns the string
	 * @return
	 */
	public String getDigit() {

		return digit;
	}

	/**
	 * Getter of numberOfGroup that returns the int
	 * @return
	 */
	public int getNumberOfGroup() {

		return numberOfGroup;
	}

	/**
	 * Getter of the level in arcadeMode that returns an int
	 * @return
	 */
	public int getLevel() {

		return level;
	}


	/**
	 * Setter of level that sets its value by taking in parameter:
	 * @param level // the level in the arcadeMode
	 */
	public void setLevel(int level) {

		this.level = level;
	}
}
