package test;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * The game frame is the main window of the game. It instantiates the model
 * and the view-controller. The frame is filled by a single panel containing
 * all the elements, which is the {@link GameViewController} object.
 *
 */
public class GameFrame extends JFrame {
	
	/**
	 * Instance of the game (logic, state, etc.)
	 */
	private GameModel gameModel;

	/**
	 * Handles both the graphical interface (tiles, labels, buttons, etc.)
	 * and the user input (mouse events, button clicks, etc.)
	 */
	private GameViewController gameViewController;
	
	/**
	 * Initializes the main properties of the game window
	 */
	public void initUI() {
		setTitle("Mean Sum");
		setSize(1200, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	/**
	 * The constructor instantiates the model and view-controller
	 * and builds the game window.
	 */
	public GameFrame() {
		// TODO Initialize our game model
		gameModel = new GameModel();
		// Initialize the interface
		initUI();
		
		// Initialize the view and set it as the main component our window
		gameViewController = new GameViewController(gameModel);
		setContentPane(gameViewController);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GameFrame game = new GameFrame();
				game.setVisible(true);
				
				
			}
		});
	}


}

