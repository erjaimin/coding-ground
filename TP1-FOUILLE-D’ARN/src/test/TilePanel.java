package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;


/**
 * The tile panel displays all the tiles (one per digit) of the game.
 *
 */
public class TilePanel extends JPanel {

	private GameModel gameModelHandle; //instance of GameModel class
	
	private String posVal; //value at a certain position in the tile array

	private Color[] tileColours; // Some tile colours in the '0xRRGGBB' format
	
	private int activeColourIndex;
	
	private int tileColour;
	
	private boolean sumChangedOnce = true; //boolean that changes if the sum of a certain value has been added once


	

	/**
	 * Initializes an array of pre-set colours, picked to
	 * ensure readability and avoid confusion.
	 */
	private void initializeColours() {
		// Some tile colours in the '0xRRGGBB' format
		String[] tileColourCodes = new String[] {
				"0xF0F8FF", "0x7FFFD4", "0x89CFF0", "0xF4C2C2", 
				"0x98777B", "0x00FFFF", "0xFFBF00", "0xFBCEB1", 
				"0x6495ED", "0x654321", "0x9BDDFF", "0xFBEC5D",
				"0xFF7F50", "0x00FFFF", "0x99BADD", "0xF0E130", "0xFFF8DC"
		};

		// Allocate and fill our colour array with the colour codes
		tileColours = new Color[tileColourCodes.length];
		for (int i = 0; i < tileColourCodes.length; i++)
			tileColours[i] = Color.decode(tileColourCodes[i]);

		activeColourIndex = 0;
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);

		// TODO Seek current game information from the model and draw the tiles accordingly
		g.setColor(tileColours[activeColourIndex]); //function of the paintCompenent that sets a color
		
		g.fillRoundRect(0, 0, 120, 80, 30, 30); //function of the paintCompenent that fills a round cornered rectangle

		g.setFont(new Font("Arial", Font.PLAIN, 55)); //function of the paintCompenent that sets the font of the writing on the tile
		
		g.setColor(Color.black); //function of the paintCompenent that sets the color of the writing
		
		g.drawString(posVal, 50, 50); //function of the paintCompenent that draws a string
	}
	
	/*
	 * void function that changes the color of a tile
	 */
	public void changeColour() {

		activeColourIndex = tileColour;

		if (activeColourIndex < tileColours.length - 1)
			++activeColourIndex;
		else
			activeColourIndex = 0;

		repaint();
	}


	/**
	 * Constructor of the class that takes in parameter:
	 * @param model //the gameModel instance
	 * @param pos //the string at the position
	 * @param colorTile //the color of the tile
	 */
	public TilePanel(GameModel model, String pos, int colorTile) {

		this.gameModelHandle = model;
		
		this.posVal = pos;
		
		this.tileColour = colorTile;
		
		initializeColours();
	}

	/**
	 * Void function that sets the color of the tile by taking in parameter:
	 * @param tileColour
	 */
	public void setTileColour(int tileColour) {
		
		this.tileColour = tileColour;
	}

	/**
	 * Getter that returns the string posVal 
	 */
	public String getValPos() {
		
		return posVal;
	}


	/**
	 * Getter that returns the int tileColour
	 */
	public int getTileColour() {
		
		return tileColour;
	}

	/**
	 * Getter that returns the boolean sumChangedOnce
	 */
	public boolean isSumChangedOnce() {
		
		return sumChangedOnce;
	}

	/**
	 * Setter that sets the boolean sumChangedOnce
	 * @param sumChangedOnce
	 */
	public void setSumChangedOnce(boolean sumChangedOnce) {
		
		this.sumChangedOnce = sumChangedOnce;
	}
}
