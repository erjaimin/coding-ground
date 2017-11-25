package mathgui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * An MathGUI (JavaFX) to perform Math operations.
 *
 */
public class MathGUI extends Application {

    // fields to hold the numbers
    private final TextField num1Field;
    private final TextField num2Field;
    private final TextField resultField;

    // font for the buttons, labels and fields
    private static final Font DEFAULT_FONT = new Font(24);

    // gap and margin sizes
    private static final int H_GAP = 8;
    private static final int V_GAP = H_GAP;
    private static final int PADDING = 5;

    /**
     * Create the text fields for this application. Only necessary because the
     * fields are final.
     */
    public MathGUI() {
        // create fields (need to reference them later)
        num1Field = makeMyField();
        num2Field = makeMyField();
        resultField = makeMyField();
    }

    /**
     * Set up the window for this application.
     *
     * @param stage the window pane this application runs in.
     */
    @Override
    public void start(Stage stage) {
        GridPane contents = createControls();

        Scene scene = new Scene(contents);
        addAccelerators(scene);

        stage.setTitle("Math Window (JavaFX Application)");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);

        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Take the numbers from <code>num1Field</code> and <code>num2Field</code>
     * and put their sum into <code>resultField</code>. Put an error message
     * into <code>resultField</code> if there's any error parsing the numbers.
     */
    private void addTheNumbers() {
        try {
            long num1 = Long.parseLong(num1Field.getText());
            long num2 = Long.parseLong(num2Field.getText());
            long sum = num1 + num2;
            resultField.setText(Long.toString(sum));
        } catch (NumberFormatException nfe) {
            resultField.setText("###Error###");
        }
    }
    
    /**
     * Take the numbers from <code>num1Field</code> and <code>num2Field</code>
     * and put their subtraction into <code>resultField</code>. Put an error message
     * into <code>resultField</code> if there's any error parsing the numbers.
     */
    private void subtractTheNumbers() {
        try {
        	long num1 = Long.parseLong(num1Field.getText());
        	long num2 = Long.parseLong(num2Field.getText());
        	long result = num1 - num2;
            resultField.setText(Long.toString(result));
        } catch (NumberFormatException nfe) {
            resultField.setText("###Error###");
        }
    }
    
    /**
     * Take the numbers from <code>num1Field</code> and <code>num2Field</code>
     * and put their multiplication into <code>resultField</code>. Put an error message
     * into <code>resultField</code> if there's any error parsing the numbers.
     */
    private void multiplayTheNumbers() {
        try {
            long num1 = Long.parseLong(num1Field.getText());
            long num2 = Long.parseLong(num2Field.getText());
            long result = num1 * num2;
            resultField.setText(Long.toString(result));
        } catch (NumberFormatException nfe) {
            resultField.setText("###Error###");
        }
    }
    
    /**
     * Take the numbers from <code>num1Field</code> and <code>num2Field</code>
     * and put their division into <code>resultField</code>. Put an error message
     * into <code>resultField</code> if there's any error parsing the numbers.
     */
    private void divideTheNumbers() {
        try {
            long num1 = Long.parseLong(num1Field.getText());
            long num2 = Long.parseLong(num2Field.getText());
            long result = num1 / num2;
            resultField.setText(Long.toString(result));
        } catch (NumberFormatException | ArithmeticException nfe) {
            resultField.setText("###Error###");
        }
    }

    /**
     * Create and activate all the components and lay out the stage.
     *
     * @return the complete window pane for this application
     */
    private GridPane createControls() {
        // create labels
        Label instructions = makeMyLabel("Enter two operands:");
        Label num1Label = makeMyLabel("First Number:");
        Label num2Label = makeMyLabel("Second Number:");
        Label resultLabel = makeMyLabel("Result:");

        // text fields were created in the constructor
        // create buttons for each operation
        Button doneButton = makeMyButton("Done");
        Button addButton = makeMyButton("\u002B");
        Button subtractButton = makeMyButton("\u2212");
        Button multiplyButton = makeMyButton("\u00D7");
        Button divideButton = makeMyButton("\u00F7");

        // attach tool tips for each button
        Tooltip.install(num1Field, new Tooltip("Enter a number here"));
        Tooltip.install(num2Field, new Tooltip("Enter another number here"));
        Tooltip.install(resultField, new Tooltip("Their sum appears here"));
        Tooltip.install(doneButton, new Tooltip("Exit the program"));
        Tooltip.install(addButton, new Tooltip("Add the two numbers together<Ctrl-P>"));
        Tooltip.install(subtractButton, new Tooltip("Subtract the two numbers<Ctrl-M>"));
        Tooltip.install(multiplyButton, new Tooltip("Multiply the two numbers<Ctrl-T>"));
        Tooltip.install(divideButton, new Tooltip("Divide the two numbers<Ctrl-D>"));

        // make the result field un-editable
        resultField.setEditable(false);

        // activate buttons click action
        doneButton.setOnAction(e -> Platform.exit());
        addButton.setOnAction(e -> addTheNumbers());
        subtractButton.setOnAction(e -> subtractTheNumbers());
        multiplyButton.setOnAction(e -> multiplayTheNumbers());
        divideButton.setOnAction(e -> divideTheNumbers());

        // put it all together in a grid
        GridPane root = createLayout();
        root.add(instructions, 0, 0, 5, 1);
        root.add(num1Label, 0, 1, 4, 1);
        root.add(num2Label, 0, 2, 4, 1);
        root.add(resultLabel, 0, 3, 4 ,1);
        root.add(num1Field, 4, 1);
        root.add(num2Field, 4, 2);
        root.add(resultField, 4, 3);
        root.addRow(4, addButton, subtractButton, multiplyButton, divideButton, doneButton);

        // center the buttons in their cells
        GridPane.setHalignment(addButton, HPos.CENTER);
        GridPane.setHalignment(subtractButton, HPos.CENTER);
        GridPane.setHalignment(multiplyButton, HPos.CENTER);
        GridPane.setHalignment(divideButton, HPos.CENTER);
        GridPane.setHalignment(doneButton, HPos.CENTER);

        return root;
    }

    /**
     * Create a label using the default font.
     *
     * @param text the text of the label
     * @return a label with the given text in the default font
     */
    private Label makeMyLabel(String text) {
        Label result = new Label(text);
        result.setFont(DEFAULT_FONT);
        return result;
    }

    /**
     * Create a right-justified text field containing zero and using the default
     * font.
     *
     * @return a right-justified text field in the default font
     */
    private TextField makeMyField() {
        TextField result = new TextField("0");
        result.setFont(DEFAULT_FONT);
        result.setAlignment(Pos.CENTER_RIGHT);
        return result;
    }

    /**
     * Create a button withe the given text and in the default font.
     *
     * @param text the text of the button
     * @return a button with the given text and in the default font
     */
    private Button makeMyButton(String text) {
        Button result = new Button(text);
        result.setFont(DEFAULT_FONT);
        return result;
    }

    /**
     * Create a grid-pane to lay out the window components in. Set margins and
     * gaps.
     *
     * @return a new GridPane with margins and gaps set
     */
    private GridPane createLayout() {
        GridPane root = new GridPane();
        root.setHgap(H_GAP);
        root.setVgap(V_GAP);
        root.setPadding(new Insets(PADDING));
        return root;
    }

    /**
     * Add several keyboard shortcuts to the scene.
     *
     * @param scene the scene to add the shortcuts to
     */
    private void addAccelerators(Scene scene) {
        // ctrl-F = first, ctrl-S = second, ctrl-R = result
        addAccelerator(scene, KeyCode.F, num1Field);
        addAccelerator(scene, KeyCode.S, num2Field);
        addAccelerator(scene, KeyCode.R, resultField);

        // ctrl-1 = first, ctrl-2 = second, ctrl-3 = result
        addAccelerator(scene, KeyCode.DIGIT1, num1Field);
        addAccelerator(scene, KeyCode.DIGIT2, num2Field);
        addAccelerator(scene, KeyCode.DIGIT3, resultField);
        
        // ctrl-P = Add, ctrl-M = Subtract, ctrl-T = Multiply, ctrl-D = Divide
        addShortcut(scene, new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN), (Runnable) () -> addTheNumbers());
        addShortcut(scene, new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN), (Runnable) () -> subtractTheNumbers());
        addShortcut(scene, new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN), (Runnable) () -> multiplayTheNumbers());
        addShortcut(scene, new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN), (Runnable) () -> divideTheNumbers());
    }

    /**
     * Add the keyboard shortcut to the scene (ctrl+key execute the action)
     * Uses SHORTCUT_DOWN instead of CTRL_DOWN so Mac users can use meta key.
     * @param scene the scene to add the shortcut to
     * @param keyCodeCombination the key combination to be pressed
     * @param runnable the method to execute on key event
     */
	private void addShortcut(Scene scene, KeyCodeCombination keyCodeCombination, Runnable r) {
		 scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
				if(keyCodeCombination.match(event)){
					r.run();
				}
			});
	}

	/**
     * Add one keyboard shortcut to the scene (ctrl+key takes you to field).
     * Uses SHORTCUT_DOWN instead of CTRL_DOWN so Mac users can use meta key.
     *
     * @param scene the scene to add the shortcut to
     * @param key   the key to be pressed (ctrl/meta will be added)
     * @param field the field to move to when the controlled key is pressed
     */
    private void addAccelerator(Scene scene, KeyCode key, TextField field) {
        scene.getAccelerators().put(
                new KeyCodeCombination(
                        key,
                        KeyCombination.SHORTCUT_DOWN),
                (Runnable) () -> {
                    field.requestFocus();
                    field.selectAll();
                });
    }

}
