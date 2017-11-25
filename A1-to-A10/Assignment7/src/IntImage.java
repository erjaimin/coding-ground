import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A class to represent a matrix of integers. It is used as a stand-in for a
 * circuit board in the WireRouting program, and can be used as a stand-in for
 * an image in a component-labeling program.
 *
 * @author Mark Young (A00000000)
 */
public class IntImage {

    // ---------- class constants -------------------------------------- //
    
    public static final int MAX_ROWS = 80;
    public static final int MAX_COLUMNS = 120;

    
    // ---------- instance variables ----------------------------------- //

    private final int WIDTH;
    private final int HEIGHT;
    private int[][] pixels;
    private boolean initialized = false;

    
    // ---------- constructors ----------------------------------------- //

    /**
     * Create a matrix of the given size. All elements initialized to zero.
     *
     * @param height the height of the matrix (number of rows)
     * @param width  the width of the matrix (number of columns)
     * 
     * @throws IllegalArgumentException if the requested size is unsuitable
     */
    public IntImage(int height, int width) {
        // check for unreasonable requested size
        if (width < 1 || height < 1
                || width > MAX_COLUMNS || height > MAX_ROWS) {
            throw new IllegalArgumentException(String.format(
                    "Illegal size: %dx%d", width, height));
        }

        // use reasonable values
        WIDTH = width;
        HEIGHT = height;
        pixels = new int[HEIGHT][WIDTH];
        initialized = true;
    }

    
    // ---------- instance methods ------------------------------------- //

    /**
     * The width of this matrix.
     *
     * @return the width of this matrix
     */
    public int getWidth() {
        return WIDTH;
    }

    /**
     * The height of this matrix.
     *
     * @return the height of this matrix
     */
    public int getHeight() {
        return HEIGHT;
    }

    /**
     * Read the elements of this matrix from the given Scanner. The Scanner must
     * contain as many lines as the height of this matrix, with each line
     * containing as many int values as the width of this matrix. If it does
     * not, the loading fails, this method returns false, and the IntImage is
     * set to an unusable state. Otherwise the values are read into the matrix
     * and this method returns true.
     *
     * @param input a Scanner containing the matrix values
     * @return true if the loading succeeded; false if it failed
     * 
     * @throws IllegalStateException if this object is unusable
     */
    public boolean load(Scanner input) {
        // make sure we're not already messed up
        checkInitialization();
        try {
            // read the values
            for (int r = 0; r < HEIGHT; ++r) {
                Scanner line = new Scanner(input.nextLine());
                for (int c = 0; c < WIDTH; ++c) {
                    pixels[r][c] = line.nextInt();
                }
            }

            // signal success
            return true;
        } catch (NoSuchElementException e) {
            // includes InputMismatchException

            // mark this object unusable
            initialized = false;

            // signal failure
            return false;
        }
    }

    /**
     * Print this matrix in a compact form. The numbers will all "run together",
     * but they'll be in base 36 (0..9, A..Z), so unless an element is 36 or
     * more each number will be only one character wide.
     * 
     * @throws IllegalStateException if this object is unusable
     */
    public void print() {
        // make sure we're usable
        checkInitialization();
        
        // print the values
        for (int r = 0; r < HEIGHT; ++r) {
            for (int c = 0; c < WIDTH; ++c) {
                System.out.print(" " +
                        Integer.toString(pixels[r][c], Character.MAX_RADIX));
            }
            System.out.println();
        }
    }

    /**
     * Retrieve the value at the given row and column.
     * 
     * @param r the row number (0-based)
     * @param c the column number (0-based)
     * @return the value at that row and column
     * 
     * @throws IllegalStateException if this object is unusable
     * @throws IllegalArgumentException if the row or column is illegal
     */
    public int get(int r, int c) {
        // check everything's OK
        checkInitialization();
        checkAddress(r, c);
        
        // return the value
        return pixels[r][c];
    }

    /**
     * Set the value at the given row and column.
     * 
     * @param r the row number (0-based)
     * @param c the column number (0-based)
     * @param v the new value for that row and column
     * 
     * @throws IllegalStateException if this object is unusable
     * @throws IllegalArgumentException if the row or column is illegal
     */
    public void set(int r, int c, int v) {
        // check everything's OK
        checkInitialization();
        checkAddress(r, c);
        
        // store the value
        pixels[r][c] = v;
    }

    /**
     * Check whether the given address is valid.
     * 
     * @param r the row (0-based)
     * @param c the column (0-based)
     * @return whether that row and column reference a location in this matrix
     */
    public boolean validAddress(int r, int c) {
        return (0 <= r && r < HEIGHT && 0 <= c && c < WIDTH);
    }
    
    
    // ---------- private methods -------------------------------------- //

    // Throw an IllegalStateException if this object is unusable.
    // It might be unusable because of a failed construction or a failed load.
    private void checkInitialization() {
        if (!initialized) {
            throw new IllegalStateException("IntImage not initialized");
        }
    }

    // Throw an IllegalArgumentException if the given location is invalid
    private void checkAddress(int r, int c) {
        if (!validAddress(r, c)) {
            throw new IllegalArgumentException(String.format(
                    "Illegal pixel address: (%d,%d)", r, c));
        }
    }

}
