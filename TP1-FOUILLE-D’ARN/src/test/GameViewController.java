package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * The view-controller class handles the display (tiles, buttons, etc.) and the
 * user input (actions from selections, clicks, etc.).
 *
 */
public class GameViewController extends JPanel {

	/**
	 * The view-controller object holds a reference to the game model to request
	 * 
	 * information to display (view) and to modify its state (controller)
	 */
	private GameModel gameModelHandle; //instance of the GameModel class

	private JButton giveUpButton; //instance of the JButton giveUpButton

	private JButton nextButton; //instance of the JButton nextButton

	private JButton resetButton; //instance of the JButton resetButton

	private JButton trainingButton;

	private JButton replayButton;

	private JButton arcadeButton;

	private JButton saveButton;

	private JButton restartButton;

	private JButton previousButton;

	private JCheckBox findMeanCheckBox; //instance of the JCheckBox findMeanCheckBox

	private JCheckBox noHelpCheckBox; //instance of the JCheckBox noHelpCheckBox

	private JLabel chronoLabel;

	private JLabel currentSumLabel; //instance of the JLabel currentSumLabel

	private JLabel goalLabel; //instance of the JLabel goalLabel

	private JLabel numberOfResetLabel; //instance of the JLabel numberOfResetLabel

	private JLabel levelLabel; //instance of the JLabel levelLabel

	private JLabel scoreLabel; //instance of the JLabel scoreLabel

	private JLabel timeToBeatLabel;

	private JPanel xPanel; //instance of the JPanel xPanel

	private JPanel xPanel2; //instance of the JPanel xPanel2

	private JPanel xPanel3; //instance of the JPanel xPanel3

	private TilePanel tilePanel; //instance of the TilePanel tilePanel

	private ArrayList<TilePanel> arrayTilePanel = new ArrayList<TilePanel>(); //instance of the ArrayList of tilePanel

	private Color oldButtonColor;

	private int goalHolder = 0; //instance of the goal for the game that wants the average

	private int lastEntered = -1; //instance of the last entered tile if it's out of border

	private int giveUpCounter=0;

	private int saveFileNum = 1;

	private int gameSaveNum = 1;

	private int loopCounter = 0;
	
 
	// Properties of Chronometer
	private int centiseconds = 0;
	private int seconds = 0;
	private int minutes = 0;

	// Properties of Chronometer
	private int centiseconds2 = 0;
	private int seconds2 = 0;
	private int minutes2 = 0;

	private DecimalFormat chronoFormatter;

	private Timer chrono;

	//instance of the number of rests
	private int numberOfResetsCounter = 0;
	private int numberOfResetsCounter2 = 0;
	/**
	 * All the listeners used in the game
	 */
	private void setupListeners() {

		//The nextButton listeners that creates a new game while keeping the type of game
		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				//reinitializing all except for the type of game
				if(gameModelHandle.isReplayMode()==true){ //&& gameSaveNum<numSaveFileInFolder){
					gameSaveNum++;
				}else{
					gameSaveNum = 1;
				}
				xPanel.removeAll();

				chrono.start();

				arrayTilePanel.clear();

				gameModelHandle.getSolutionArray().clear();

				gameModelHandle.setGoalSum(0);

				gameModelHandle.setSum(0);

				gameModelHandle.setRegroupCounter(0);

				tileInitializer(gameModelHandle);

				gameModelHandle.setAllTilesChosen(false);

				if(gameModelHandle.isSumGame() == false) {

					//reinitializing all except for the type of game
					xPanel.removeAll(); 

					arrayTilePanel.clear();

					gameModelHandle.getSolutionArray().clear();

					gameModelHandle.setGoalSum(0);

					gameModelHandle.setSum(0);

					gameModelHandle.setRegroupCounter(0);

					tileInitializer(gameModelHandle);

					//gameModelHandle.AverageGame();

					findMeanCheckBox.setSelected(true);

					goalHolder=gameModelHandle.getGoalSum()/gameModelHandle.getNumberOfGroup();

					currentSumLabel.setText("Current sum: " + gameModelHandle.getSum() + "" + "/(" + gameModelHandle.getRegroupCounter() + ")");

					goalLabel.setText("Goal: " + goalHolder);
				}

				numberOfResetsCounter = 0;
				numberOfResetLabel.setText(" [Resets] " + numberOfResetsCounter);
				if(gameModelHandle.isTrainingMode()==true) {

					trainingButton.setBackground(Color.green);
					arcadeButton.setEnabled(true);
					trainingButton.setEnabled(false);
					replayButton.setEnabled(true);

				} else if(gameModelHandle.isArcadeMode()==true) {

					arcadeButton.setBackground(Color.green);
					arcadeButton.setEnabled(false);
					trainingButton.setEnabled(true);
					replayButton.setEnabled(true);
					gameModelHandle.setLevel(gameModelHandle.getLevel() + 1);
					levelLabel.setText(" [Level] " + gameModelHandle.getLevel());
					scoreLabel.setText(" [Score] " + gameModelHandle.getArcadeScore());

				} else if(gameModelHandle.isReplayMode()==true) {

					replayButton.setBackground(Color.green);
					arcadeButton.setEnabled(true);
					trainingButton.setEnabled(true);
					replayButton.setEnabled(false);
					findMeanCheckBox.setEnabled(false);
					noHelpCheckBox.setEnabled(false);
					numberOfResetLabel.setText(" [Resets] " + numberOfResetsCounter + " (" + numberOfResetsCounter2 + ")");
					previousButton.setVisible(true);
				}

				if(noHelpCheckBox.isSelected()==true){
					currentSumLabel.setVisible(false);
				}

				loopCounter = 0;

			}
		});

		//The nextButton listeners that creates a previous game while keeping the type of game in replay mode
//		previousButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				//reinitializing all except for the type of game
//				if(gameModelHandle.isReplayMode()==true && gameSaveNum>1){
//					gameSaveNum--;
//				}else{
//					gameSaveNum = numSaveFileInFolder ;
//				}
//				xPanel.removeAll();
//
//				chrono.start();
//
//				arrayTilePanel.clear();
//
//				gameModelHandle.getSolutionArray().clear();
//
//				gameModelHandle.setGoalSum(0);
//
//				gameModelHandle.setSum(0);
//
//				gameModelHandle.setRegroupCounter(0);
//
//				tileInitializer(gameModelHandle);
//
//				gameModelHandle.setAllTilesChosen(false);
//
//				if(gameModelHandle.isSumGame() == false) {
//
//					//reinitializing all except for the type of game
//					xPanel.removeAll(); 
//
//					arrayTilePanel.clear();
//
//					gameModelHandle.getSolutionArray().clear();
//
//					gameModelHandle.setGoalSum(0);
//
//					gameModelHandle.setSum(0);
//
//					gameModelHandle.setRegroupCounter(0);
//
//					tileInitializer(gameModelHandle);
//
//					//gameModelHandle.AverageGame();
//
//					findMeanCheckBox.setSelected(true);
//
//					goalHolder=gameModelHandle.getGoalSum()/gameModelHandle.getNumberOfGroup();
//
//					currentSumLabel.setText("Current sum: " + gameModelHandle.getSum() + "" + "/(" + gameModelHandle.getRegroupCounter() + ")");
//
//					goalLabel.setText("Goal: " + goalHolder);
//				}
//
//				numberOfResetsCounter = 0;
//				numberOfResetLabel.setText(" [Resets] " + numberOfResetsCounter);
//
//
//				if(gameModelHandle.isReplayMode()==true) {
//
//					replayButton.setBackground(Color.green);
//					arcadeButton.setEnabled(true);
//					trainingButton.setEnabled(true);
//					replayButton.setEnabled(false);
//					findMeanCheckBox.setEnabled(false);
//					noHelpCheckBox.setEnabled(false);
//					numberOfResetLabel.setText(" [Resets] " + numberOfResetsCounter + " (" + numberOfResetsCounter2 + ")");
//				}
//
//				if(noHelpCheckBox.isSelected()==true){
//					currentSumLabel.setVisible(false);
//				}
//			}
//		});

		//The reset button listener that resets the values stored and the colors
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				//resetting the values to the beginning of the game
				gameModelHandle.setSum(0);

				gameModelHandle.setRegroupCounter(0);

				currentSumLabel.setText("Current sum: " + gameModelHandle.getSum() + "" + " (" + gameModelHandle.getRegroupCounter() + ")");



				for (int i = 0; i < arrayTilePanel.size(); i++) {

					arrayTilePanel.get(i).setTileColour(16);

					arrayTilePanel.get(i).changeColour();

					arrayTilePanel.get(i).setSumChangedOnce(true);
				}

				gameModelHandle.resetSelectionArray();

				gameModelHandle.setAllTilesChosen(false);

				if(gameModelHandle.isSumGame() == false) {

					currentSumLabel.setText("Current sum: " + gameModelHandle.getSum() + "/(" + gameModelHandle.getRegroupCounter() + ")");
				}
				numberOfResetsCounter++;
				numberOfResetLabel.setText(" [Resets] " + numberOfResetsCounter);
				centiseconds = 0;
				seconds = 0;
				minutes = 0;
				chronoLabel.setText(" [Time] " + chronoFormatter.format(minutes) + ":" 
						+ chronoFormatter.format(seconds));

				if(gameModelHandle.isReplayMode()==true){
					numberOfResetLabel.setText(" [Resets] " + numberOfResetsCounter + " (" + numberOfResetsCounter2 + ")");


				}
				chrono.start(); 
				lossVerificator();
			}
		});

		//the giveUpButton listener that shows the solution and good colors of each group
		giveUpButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				currentSumLabel.setText("Current sum: " + gameModelHandle.getGoalSum() + "" + " (" + gameModelHandle.getNumberOfGroup() + ")");

				if(gameModelHandle.isSumGame() == false) {

					currentSumLabel.setText("Current sum: " + gameModelHandle.getGoalSum() + "/(" + gameModelHandle.getNumberOfGroup() + ")");
				}

				for (int i = 0; i < gameModelHandle.getNumberOfGroup(); i++) {

					for (int j = 0; j < gameModelHandle.getSolutionArray().size(); j++) {

						if(gameModelHandle.getSolutionArray().get(j) == i) {

							arrayTilePanel.get(j).setTileColour(i);

							arrayTilePanel.get(j).changeColour();

							arrayTilePanel.get(j).setSumChangedOnce(true);
						}
					}
				}

				if(gameModelHandle.isArcadeMode()==true) {

					giveUpCounter++;

				} else {

					giveUpCounter = 0;
				}

			}
		});

		trainingButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				gameModelHandle.setTrainingMode(true);
				gameModelHandle.setArcadeMode(false);
				gameModelHandle.setReplayMode(false);

				//reinitializing all except for the type of game
				xPanel.removeAll();

				chrono.start();

				arrayTilePanel.clear();

				gameModelHandle.getSolutionArray().clear();

				gameModelHandle.setGoalSum(0);

				gameModelHandle.setSum(0);

				gameModelHandle.setRegroupCounter(0);

				tileInitializer(gameModelHandle);

				gameModelHandle.setAllTilesChosen(false);

				if(gameModelHandle.isSumGame() == false) {

					//reinitializing all except for the type of game
					xPanel.removeAll(); 

					arrayTilePanel.clear();

					gameModelHandle.getSolutionArray().clear();

					gameModelHandle.setGoalSum(0);

					gameModelHandle.setSum(0);

					gameModelHandle.setRegroupCounter(0);

					tileInitializer(gameModelHandle);

					//gameModelHandle.AverageGame();

					findMeanCheckBox.setSelected(true);

					goalHolder=gameModelHandle.getGoalSum()/gameModelHandle.getNumberOfGroup();

					currentSumLabel.setText("Current sum: " + gameModelHandle.getSum() + "" + "/(" + gameModelHandle.getRegroupCounter() + ")");

					goalLabel.setText("Goal: " + goalHolder);
				}

				numberOfResetsCounter = 0;
				numberOfResetLabel.setText(" [Resets] " + numberOfResetsCounter);

				trainingButton.setBackground(Color.green);
				replayButton.setBackground(oldButtonColor);
				arcadeButton.setBackground(oldButtonColor);

				if(gameModelHandle.isTrainingMode()==true){
					arcadeButton.setEnabled(true);
					trainingButton.setEnabled(false);
					replayButton.setEnabled(true);
					gameModelHandle.setLevel(1);
				}
			}
		});

		replayButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				gameModelHandle.setTrainingMode(false);
				gameModelHandle.setArcadeMode(false);
				gameModelHandle.setReplayMode(true);
				previousButton.setVisible(true);

				//reinitializing all except for the type of game
				xPanel.removeAll();

				chrono.start();

				arrayTilePanel.clear();

				gameModelHandle.getSolutionArray().clear();

				gameModelHandle.setGoalSum(0);

				gameModelHandle.setSum(0);

				gameModelHandle.setRegroupCounter(0);

				tileInitializer(gameModelHandle);

				gameModelHandle.setAllTilesChosen(false);

				if(gameModelHandle.isSumGame() == false) {

					//reinitializing all except for the type of game
					xPanel.removeAll(); 

					arrayTilePanel.clear();

					gameModelHandle.getSolutionArray().clear();

					gameModelHandle.setGoalSum(0);

					gameModelHandle.setSum(0);

					gameModelHandle.setRegroupCounter(0);

					tileInitializer(gameModelHandle);

					//gameModelHandle.AverageGame();

					findMeanCheckBox.setSelected(true);

					goalHolder=gameModelHandle.getGoalSum()/gameModelHandle.getNumberOfGroup();

					currentSumLabel.setText("Current sum: " + gameModelHandle.getSum() + "" + "/(" + gameModelHandle.getRegroupCounter() + ")");

					goalLabel.setText("Goal: " + goalHolder);
				}

				numberOfResetsCounter = 0;
				numberOfResetLabel.setText(" [Resets] " + numberOfResetsCounter + " (" + numberOfResetsCounter2 + ")");


				gameModelHandle.setTrainingMode(false);
				gameModelHandle.setArcadeMode(false);
				gameModelHandle.setReplayMode(true);

				replayButton.setBackground(Color.green);
				arcadeButton.setBackground(oldButtonColor);
				trainingButton.setBackground(oldButtonColor);

				if(gameModelHandle.isReplayMode()==true){
					arcadeButton.setEnabled(true);
					trainingButton.setEnabled(true);
					replayButton.setEnabled(false);
					gameModelHandle.setLevel(1);
				}
				findMeanCheckBox.setEnabled(false);
				noHelpCheckBox.setEnabled(false);

				if(noHelpCheckBox.isSelected()==true){
					currentSumLabel.setVisible(false);
				}
			}
		});

		arcadeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				gameModelHandle.setTrainingMode(false);
				gameModelHandle.setArcadeMode(true);
				gameModelHandle.setReplayMode(false);

				//reinitializing all except for the type of game
				xPanel.removeAll();

				chrono.start();

				arrayTilePanel.clear();

				gameModelHandle.getSolutionArray().clear();

				gameModelHandle.setGoalSum(0);

				gameModelHandle.setSum(0);

				gameModelHandle.setRegroupCounter(0);

				tileInitializer(gameModelHandle);

				gameModelHandle.setAllTilesChosen(false);

				if(gameModelHandle.isSumGame() == false) {

					//reinitializing all except for the type of game
					xPanel.removeAll(); 

					arrayTilePanel.clear();

					gameModelHandle.getSolutionArray().clear();

					gameModelHandle.setGoalSum(0);

					gameModelHandle.setSum(0);

					gameModelHandle.setRegroupCounter(0);

					tileInitializer(gameModelHandle);

					//gameModelHandle.AverageGame();

					findMeanCheckBox.setSelected(true);

					goalHolder=gameModelHandle.getGoalSum()/gameModelHandle.getNumberOfGroup();

					currentSumLabel.setText("Current sum: " + gameModelHandle.getSum() + "" + "/(" + gameModelHandle.getRegroupCounter() + ")");

					goalLabel.setText("Goal: " + goalHolder);
				}

				numberOfResetsCounter = 0;
				numberOfResetLabel.setText(" [Resets] " + numberOfResetsCounter);

				arcadeButton.setBackground(Color.green);
				trainingButton.setBackground(oldButtonColor);
				replayButton.setBackground(oldButtonColor);

				if(gameModelHandle.isArcadeMode()==true){
					arcadeButton.setEnabled(false);
					trainingButton.setEnabled(true);
					replayButton.setEnabled(true);
				}

				gameModelHandle.setArcadeScore(0);
			}
		});

		restartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				gameModelHandle.setLevel(1);
				gameModelHandle.setTrainingMode(false);
				gameModelHandle.setArcadeMode(true);
				gameModelHandle.setReplayMode(false);

				//reinitializing all except for the type of game
				xPanel.removeAll();

				chrono.start();

				arrayTilePanel.clear();

				gameModelHandle.getSolutionArray().clear();

				gameModelHandle.setGoalSum(0);

				gameModelHandle.setSum(0);

				gameModelHandle.setRegroupCounter(0);

				tileInitializer(gameModelHandle);

				gameModelHandle.setAllTilesChosen(false);

				if(gameModelHandle.isSumGame() == false) {

					//reinitializing all except for the type of game
					xPanel.removeAll(); 

					arrayTilePanel.clear();

					gameModelHandle.getSolutionArray().clear();

					gameModelHandle.setGoalSum(0);

					gameModelHandle.setSum(0);

					gameModelHandle.setRegroupCounter(0);

					tileInitializer(gameModelHandle);

					//gameModelHandle.AverageGame();

					findMeanCheckBox.setSelected(true);

					goalHolder=gameModelHandle.getGoalSum()/gameModelHandle.getNumberOfGroup();

					currentSumLabel.setText("Current sum: " + gameModelHandle.getSum() + "" + "/(" + gameModelHandle.getRegroupCounter() + ")");

					goalLabel.setText("Goal: " + goalHolder);
				}

				numberOfResetsCounter = 0;
				numberOfResetLabel.setText(" [Resets] " + numberOfResetsCounter);

				arcadeButton.setBackground(Color.green);
				trainingButton.setBackground(oldButtonColor);
				replayButton.setBackground(oldButtonColor);

				if(gameModelHandle.isArcadeMode()==true){
					arcadeButton.setEnabled(false);
					trainingButton.setEnabled(true);
					replayButton.setEnabled(true);
				}

				giveUpCounter = 0;	
				gameModelHandle.setArcadeScore(0);
			}
		});

//		saveButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				saveMode();
//				numSaveFileInFolder = new File("../../workspace/MeanSum-SohaibOuarrak/GameSaves").listFiles().length;
//				saveButton.setEnabled(false);
//
//			}
//		});

		/*
		 * the findMeanCheckBox listener that change the 
		 * type of game (search for the sum or search for the average)
		 */
		findMeanCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				gameModelHandle.setSumGame(!gameModelHandle.isSumGame());

				if(gameModelHandle.isSumGame() == true) {

					xPanel.removeAll();

					arrayTilePanel.clear();

					gameModelHandle.getSolutionArray().clear();

					gameModelHandle.setGoalSum(0);

					gameModelHandle.setSum(0);

					gameModelHandle.setRegroupCounter(0);

					tileInitializer(gameModelHandle);

					gameModelHandle.setAllTilesChosen(false);

				} else {

					xPanel.removeAll();

					arrayTilePanel.clear();

					gameModelHandle.getSolutionArray().clear();

					gameModelHandle.setGoalSum(0);

					gameModelHandle.setSum(0);

					gameModelHandle.setRegroupCounter(0);

					tileInitializer(gameModelHandle);

					//gameModelHandle.AverageGame();

					gameModelHandle.setAllTilesChosen(false);

					goalHolder=gameModelHandle.getGoalSum()/gameModelHandle.getNumberOfGroup();

					currentSumLabel.setText("Current sum: " + gameModelHandle.getSum() + "/(" + gameModelHandle.getRegroupCounter() + ")");

					goalLabel.setText("Goal: " + goalHolder);

					findMeanCheckBox.setSelected(true);
				}
			}
		});

		/*
		 * the noHelpCheckBox listener that change the 
		 * type of game by removing or keeping some informations of the game
		 */
		noHelpCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				noHelpCheckBox.setSelected(noHelpCheckBox.isSelected());

				currentSumLabel.setVisible(!currentSumLabel.isVisible());
			}
		});

		for (int i = 0; i < arrayTilePanel.size(); i++) {

			final Integer innerI = new Integer(i);

			arrayTilePanel.get(i).addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {

					if(lastEntered == (innerI + 1)) {

						if(gameModelHandle.getSelectionArray().get(lastEntered) == 0 && gameModelHandle.getSelectionArray().get(innerI) == 0 ) {

							gameModelHandle.selectionStorer(innerI, lastEntered, arrayTilePanel.get(innerI).getTileColour(), arrayTilePanel.get(innerI).getTileColour(), arrayTilePanel);

							arrayTilePanel.get(lastEntered).setTileColour(innerI);

							arrayTilePanel.get(innerI).setTileColour(innerI);

							arrayTilePanel.get(innerI).changeColour();

							gameModelHandle.updateSum(arrayTilePanel.get(innerI).getValPos() + arrayTilePanel.get(lastEntered).getValPos());

							currentSumLabel.setText("Current sum: " + gameModelHandle.getSum() + "" + " (" + gameModelHandle.getRegroupCounter() + ")");

							if(gameModelHandle.isSumGame() == false) {

								currentSumLabel.setText("Current sum: " + gameModelHandle.getSum() + " /(" + gameModelHandle.getRegroupCounter() + ")");
							}

							arrayTilePanel.get(lastEntered).changeColour();

							allTilesChosenVerificator();

							successVerificator();

							lossVerificator();
						}

					}else if (lastEntered == innerI) {

						if(gameModelHandle.getSelectionArray().get(innerI) == 0 ) {

							gameModelHandle.selectionStorer(innerI, lastEntered, arrayTilePanel.get(innerI).getTileColour(), arrayTilePanel.get(lastEntered).getTileColour(), arrayTilePanel);

							if(arrayTilePanel.get(innerI).isSumChangedOnce()) {

								gameModelHandle.updateSum(arrayTilePanel.get(innerI).getValPos());

								currentSumLabel.setText("Current sum: " + gameModelHandle.getSum() + "" + " (" + gameModelHandle.getRegroupCounter() + ")");

								if(gameModelHandle.isSumGame() == false) {

									currentSumLabel.setText("Current sum: " + gameModelHandle.getSum() + " /(" + gameModelHandle.getRegroupCounter() + ")");
								}

								arrayTilePanel.get(innerI).setSumChangedOnce(false);

								arrayTilePanel.get(innerI).setTileColour(innerI);

								arrayTilePanel.get(innerI).changeColour();

								allTilesChosenVerificator();

								successVerificator();
							}

							allTilesChosenVerificator();

							successVerificator();

							lossVerificator();
						}

						allTilesChosenVerificator();

						successVerificator();

						lossVerificator();
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseExited(MouseEvent e) {

					lastEntered = -1;
				}

				@Override
				public void mouseEntered(MouseEvent e) {

					lastEntered = innerI;
				}

				@Override
				public void mouseClicked(MouseEvent e) {

					if(gameModelHandle.getSelectionArray().get(innerI) == 0) {

						arrayTilePanel.get(innerI).setTileColour(innerI);

						arrayTilePanel.get(innerI).changeColour();

						allTilesChosenVerificator();

						successVerificator();

						lossVerificator();
					}
				}
			});
		}
	}

	
	
	
	// new fonction 
	
	
	
	public GameViewController(GameModel gameModel) {

		if (gameModel == null)
			throw new IllegalArgumentException("Should provide a valid instance of GameModel");


		tileInitializer(gameModel);
		chronometer();

	}

	/**
	 * Void function that initiliazes the the tile panels with all its function and options
	 * on the JPanel
	 * 
	 * @param gameModel
	 */
	private void tileInitializer(GameModel gameModel) {

		this.removeAll();

		centiseconds = 0;
		seconds = 0;
		minutes = 0;

		gameModelHandle = gameModel;

		// The layout defines how components are displayed
		// (here, stacked along the Y axis)
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.xPanel = new JPanel(); 

		xPanel.setLayout(new BoxLayout(xPanel, BoxLayout.X_AXIS)); //setting the x layout for the xPanel

		xPanel.setPreferredSize(new Dimension(0,148));

		this.add(xPanel); //adding the xPanel to the JPanel

		this.xPanel2 = new JPanel(); 

		//xPanel2.setLayout(new BoxLayout(xPanel2, BoxLayout.X_AXIS)); //setting the x layout for the xPanel2

		GridLayout gridLayout = new GridLayout(0,2);
		gridLayout.setHgap(0);
		xPanel2.setLayout(gridLayout);

		if(gameModelHandle.isArcadeMode()==true){

			GridLayout gridLayout2 = new GridLayout(0,4);
			gridLayout2.setHgap(0);
			xPanel2.setLayout(gridLayout2);

		}

		this.add(xPanel2); //adding the xPanel to the JPanel2

		this.xPanel3 = new JPanel(); 

		xPanel3.setLayout(new BoxLayout(xPanel3, BoxLayout.X_AXIS)); //setting the x layout for the xPanel3

		this.add(xPanel3); //adding the xPanel to the JPanel3

		gameModelHandle.generateGroup(); //Generating the group of numbers

		gameModelHandle.generateSumGame(); //Generating game numbers

		gameModelHandle.storeDigit(); //storing the digits

		if(gameModelHandle.isReplayMode()==true){
			gameSaveReplay();

		}
		for (int i = 0; i < gameModelHandle.getDigit().length(); i++) {

			tilePanel = new TilePanel(gameModelHandle, ""+gameModelHandle.getDigit().charAt(i), i);

			arrayTilePanel.add(tilePanel);

			xPanel.add(arrayTilePanel.get(i));
		}

		chronoLabel = new JLabel(); //Creating the chornometer's Label

		chronoLabel.setFont(new Font("Arial", Font.BOLD, 13));

		chronoLabel.setHorizontalAlignment(JLabel.LEFT);

		xPanel2.add(chronoLabel);

		chronoLabel.setBorder(BorderFactory.createLineBorder(Color.black));

		numberOfResetLabel = new JLabel(" [Resets] " + numberOfResetsCounter); //Creating the chornometer's Label

		numberOfResetLabel.setFont(new Font("Arial", Font.BOLD, 13));

		numberOfResetLabel.setHorizontalAlignment(JLabel.LEFT);

		xPanel2.add(numberOfResetLabel);

		numberOfResetLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		if(gameModelHandle.isReplayMode()==true){
			timeToBeatLabel = new JLabel(" [Time TO BEAT] " + chronoFormatter.format(minutes2) + ":" 
					+ chronoFormatter.format(seconds2)); //Creating the goalLabel
			this.add(timeToBeatLabel);
		}




		goalLabel = new JLabel("Goal: " + gameModelHandle.getGoalSum()); //Creating the goalLabel

		this.add(goalLabel);

		currentSumLabel = new JLabel("Current sum: " + 0 + " (" + gameModelHandle.getRegroupCounter() + ")"); //Creating the currentSumLabel

		this.add(currentSumLabel); //Adding to the JPanel the currentSumLabel


		nextButton = new JButton("Next"); //Creating the nextButton

		this.add(nextButton); //Adding to the JPanel the nextButton


		previousButton = new JButton("Previous"); //Creating the previousButton

		this.add(previousButton); //Adding to the JPanel the previousButton

		if(gameModelHandle.isReplayMode()==false)
			previousButton.setVisible(false);

		giveUpButton = new JButton("Give Up"); //Creating the giveUpButton

		this.add(giveUpButton); //Adding to the JPanel the giveUpButton

		resetButton = new JButton("Reset"); //Creating the resetButton

		this.add(resetButton); //Adding to the JPanel the resetButton

		trainingButton = new JButton("TRAINING"); //Creating the trainingButton

		xPanel3.add(trainingButton); //Adding to the JPanel the trainingButton

		if(gameModelHandle.isTrainingMode()==true)
			trainingButton.setEnabled(false);

		replayButton = new JButton("REPLAY"); //Creating the replayButton

		xPanel3.add(replayButton); //Adding to the JPanel the replayButton

		arcadeButton = new JButton("ARCADE"); //Creating the arcadeButton

		xPanel3.add(arcadeButton); //Adding to the JPanel the arcadeButton

		saveButton = new JButton("SAVE"); //Creating the saveButton

		this.add(saveButton); //Adding to the JPanel the saveButton

		saveButton.setEnabled(false);

		if(gameModelHandle.isArcadeMode()==true||gameModelHandle.isReplayMode()==true){

			saveButton.setVisible(false);

		} else {

			saveButton.setVisible(true);
		}

		restartButton = new JButton("RESTART"); //Creating the saveButton

		this.add(restartButton); //Adding to the JPanel the saveButton

		if(gameModelHandle.isTrainingMode()==true||gameModelHandle.isReplayMode()==true){

			restartButton.setVisible(false);

		} else {

			restartButton.setVisible(true);
		}

		findMeanCheckBox = new JCheckBox("Find Mean"); //Creating the findMeanCheckBox

		this.add(findMeanCheckBox); //Adding to the JPanel the findMeanCheckBox

		noHelpCheckBox = new JCheckBox("No Help"); //Creating the noHelpCheckBox

		this.add(noHelpCheckBox); //Adding to the JPanel the noHelpCheckBox

		centiseconds = 0;
		seconds = 0;
		minutes = 0;


		if(gameModelHandle.isReplayMode()==true){
			gameSaveReplay();
		}

		if(gameModelHandle.isTrainingMode()==true){

			trainingButton.setBackground(Color.green);
		}

		oldButtonColor = nextButton.getBackground();

		if(gameModelHandle.isArcadeMode()==true){

			nextButton.setEnabled(false);
			findMeanCheckBox.setEnabled(false);
			noHelpCheckBox.setEnabled(false);
			xPanel2.removeAll();

			levelLabel = new JLabel(" [Level] " + gameModelHandle.getLevel()); //Creating the chornometer's Label

			levelLabel.setFont(new Font("Arial", Font.BOLD, 13));

			levelLabel.setHorizontalAlignment(JLabel.LEFT);

			xPanel2.add(levelLabel);

			xPanel2.add(chronoLabel);

			xPanel2.add(numberOfResetLabel);

			scoreLabel = new JLabel(" [Score] " + gameModelHandle.getArcadeScore()); //Creating the chornometer's Label

			scoreLabel.setFont(new Font("Arial", Font.BOLD, 13));

			scoreLabel.setHorizontalAlignment(JLabel.LEFT);

			xPanel2.add(scoreLabel);

			levelLabel.setBorder(BorderFactory.createLineBorder(Color.black));

			scoreLabel.setBorder(BorderFactory.createLineBorder(Color.black));

			generateArcadeMode();

		}

		if(gameModelHandle.isArcadeMode()==false){

			giveUpCounter=0;
		}

		setupListeners();

		this.repaint();

		this.validate();

	}

	/**
	 * Void function that controls the chronometer and sets the text in the chronoLabel during all the modes
	 */
	private void chronometer() {
		chronoFormatter = new DecimalFormat("00");

		chrono = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (centiseconds < 99)
					centiseconds++;
				else
				{

					if (seconds < 59)
					{
						seconds++;
						centiseconds = 0;
					}
					else if (minutes < 59)
					{
						minutes++;
						seconds = 0;
						centiseconds = 0;
					}
				}
				chronoLabel.setText(" [Time] " + chronoFormatter.format(minutes) + ":" 
						+ chronoFormatter.format(seconds));
				lossVerificator();
				if(seconds==30)
					gameModelHandle.setArcadeScore((gameModelHandle.getArcadeScore()-2));

				if(gameModelHandle.getArcadeScore()<0)
					gameModelHandle.setArcadeScore(0);

			}
		});


		chrono.start();
	}

	/**
	 * Void function that verifies if the state of the boolean allTilesChosen has been changed
	 */
	private void allTilesChosenVerificator() {

		int chosenTileNumber = 0;

		for (int i = 0; i < gameModelHandle.getSelectionArray().size(); i++) {

			if(gameModelHandle.getSelectionArray().get(i) != 0) {

				chosenTileNumber++;
			}
		}

		if(chosenTileNumber == gameModelHandle.getSelectionArray().size()) {

			gameModelHandle.setAllTilesChosen(true);
		}
	}

	/**
	 * Void function that verifies if the game has been won and sets the appropriate color
	 */
	private void successVerificator() {

		if(gameModelHandle.isAllTilesChosen() == true) {

			if(gameModelHandle.getGoalSum() == gameModelHandle.getSum()) {

				chrono.stop();

				for (int i = 0; i < arrayTilePanel.size(); i++) {

					arrayTilePanel.get(i).setTileColour(0);

					arrayTilePanel.get(i).changeColour();

					saveButton.setEnabled(true);

					nextButton.setEnabled(true);

					resetButton.setEnabled(false);

					giveUpButton.setEnabled(false);

					if(giveUpCounter!=0){

						nextButton.setEnabled(false);
					}
					if(gameModelHandle.isArcadeMode()==true&&gameModelHandle.getLevel()==20){
						nextButton.setEnabled(false);
					}

				}
				if(gameModelHandle.isArcadeMode()==true){

					if(loopCounter==0)
						gameModelHandle.arcadeScoreCalculator(noHelpCheckBox.isSelected(), numberOfResetsCounter);

					scoreLabel.setText(" [Score] " + gameModelHandle.getArcadeScore());

				}
				loopCounter++;

			}
		}
	}

	/**
	 * Void function that verifies if the player lost and sets the appropriate color
	 */
	private void lossVerificator() {

		if(gameModelHandle.isReplayMode()==true){
			if(numberOfResetsCounter>numberOfResetsCounter2){
				chrono.stop();


				for (int i = 0; i < arrayTilePanel.size(); i++) {

					arrayTilePanel.get(i).setTileColour(11);

					arrayTilePanel.get(i).changeColour();

					resetButton.setEnabled(false);

				}

			}

			if(seconds==seconds2&&centiseconds==centiseconds2&&minutes==minutes2){
				chrono.stop();


				for (int i = 0; i < arrayTilePanel.size(); i++) {

					arrayTilePanel.get(i).setTileColour(11);

					arrayTilePanel.get(i).changeColour();



				}	
			}
		}

		if(gameModelHandle.isAllTilesChosen() == true) {

			if(gameModelHandle.getGoalSum() != gameModelHandle.getSum()) {

				chrono.stop();


				for (int i = 0; i < arrayTilePanel.size(); i++) {

					arrayTilePanel.get(i).setTileColour(11);

					arrayTilePanel.get(i).changeColour();
				}
			}
		}
	}

	/**
	 * Void function that generates the arcade mode's settings
	 */
	public void generateArcadeMode() {

		Random rand = new Random(); //random value
		double meanProba = 10 * (0.1 + (0.9 * gameModelHandle.getLevel() / 20)); 
		double noHelpProba = 10 * (0.1 + (0.9 * gameModelHandle.getLevel() / 20));
		double randomNumber = rand.nextInt(10) + 1; //random number generating a value between 1 and 10 

		if(randomNumber <= meanProba + 1) {

			gameModelHandle.setSumGame(false);
			findMeanCheckBox.setSelected(true);

		}else {

			gameModelHandle.setSumGame(true);
			findMeanCheckBox.setSelected(false);
		}

		if(randomNumber  <= noHelpProba + 1) {

			noHelpCheckBox.setSelected(true);
			currentSumLabel.setVisible(false);

		} else{

			noHelpCheckBox.setSelected(false);
			currentSumLabel.setVisible(true);

		}
	}

	/**
	 * Void function that controls the saving by creating a file and 
	 * writing the important values on it after a win in the training mode if the saveButton has been clicked
	 */
	public void saveMode() {

		try {

			saveFileNum = 1;
			//String content = "This is the content to write into file" + gameModelHandle.getValue();
			String content="";
			File file = new File("GameSaves/Save "+saveFileNum+".txt");

			while (file.exists()) {

				saveFileNum++;

				file = new File("GameSaves/Save "+saveFileNum+".txt");
			}

			// if file doesnt exists, then create it
			if (!file.exists()) {

				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("" + gameModelHandle.isSumGame());
			bw.newLine();
			bw.write("" + noHelpCheckBox.isSelected());
			bw.newLine();
			bw.write(""+gameModelHandle.getNumberOfGroup());
			bw.newLine();
			content = "";
			for (int i = 0; i < gameModelHandle.getNumberHolder().size(); i++) {

				content = content + gameModelHandle.getNumberHolder().get(i) + "/";
			}
			bw.write(content);
			bw.newLine();
			bw.write(gameModelHandle.getDigit());
			bw.newLine();
			content = "";
			for (int i = 0; i < gameModelHandle.getSolutionArray().size(); i++) {

				content = content + gameModelHandle.getSolutionArray().get(i) + "/";
			}
			bw.write(content);
			bw.newLine();
			bw.write("" + gameModelHandle.getGoalSum());
			bw.newLine();
			bw.write("" + numberOfResetsCounter);
			bw.newLine();
			bw.write("" + centiseconds);
			bw.newLine();
			bw.write("" + seconds);
			bw.newLine();
			bw.write("" +  minutes);
			bw.close();


			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	/**
	 * Void function that controls the replay of saved games by reading the file in a specified location and by setting
	 * the important values of the game by the ones in the save file for replaying the games
	 */
	public void gameSaveReplay() {
		//System.out.println(numSaveFileInFolder);
		try (BufferedReader br = new BufferedReader(new FileReader("GameSaves/Save " + gameSaveNum + ".txt")))
		{
			String sCurrentLine;
			sCurrentLine = "";
			gameModelHandle.setSumGame(Boolean.parseBoolean(br.readLine()));
			noHelpCheckBox.setSelected(Boolean.parseBoolean(br.readLine()));
			gameModelHandle.setNumberOfGroup(Integer.parseInt(br.readLine()));
			sCurrentLine = br.readLine();
			ArrayList<String> strArrayList = new ArrayList<String>(Arrays.asList(sCurrentLine.split("/")));
			ArrayList<Integer> intArrayList = new ArrayList<Integer>();
			for (int i = 0; i < strArrayList.size(); i++) {

				intArrayList.add(Integer.parseInt(strArrayList.get(i)));
			}

			gameModelHandle.setNumberHolder(intArrayList);
			gameModelHandle.setDigit(br.readLine());
			sCurrentLine = br.readLine();
			ArrayList<String> strArrayList2 = new ArrayList<String>(Arrays.asList(sCurrentLine.split("/")));
			ArrayList<Integer> intArrayList2 = new ArrayList<Integer>();
			for (int i = 0; i < strArrayList2.size(); i++) {

				intArrayList2.add(Integer.parseInt(strArrayList2.get(i)));
			}

			gameModelHandle.setSolutionArray(intArrayList2);
			gameModelHandle.setGoalSum(Integer.parseInt(br.readLine()));
			numberOfResetsCounter2 = Integer.parseInt(br.readLine());
			centiseconds2 = Integer.parseInt(br.readLine());
			seconds2 = Integer.parseInt(br.readLine());
			minutes2 = Integer.parseInt(br.readLine());
			gameModelHandle.getSelectionArray().clear();
			ArrayList<Integer> tempArray = new ArrayList<Integer>();
			for (int i = 0; i < gameModelHandle.getDigit().length(); i++) {

				tempArray.add(0); //filling the selection array with 0's
			}
			gameModelHandle.setSelectionArray(tempArray);

		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
