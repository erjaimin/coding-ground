import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Implement the component labeler program that groups and labels the area with
 * same color by separating foreground items for computer vision
 * 
 * @author Khalid Alzhrani (A00361007)
 *
 */
public class ComponentLabeler {

	private static final Scanner scanner = new Scanner(System.in);

	/**
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		displayIntroduction();

		// read image
		IntImage image = readImage();

		// scan the image and look for unlabeled cells and label them
		labelComponents(image);

		System.out.println("Here is the revised image:");
		image.print();
		pause();
	}

	/**
	 * This method scan the image cell by cell and check for unlabeled cell
	 * (cell value = 1) and continue further labeling its neighbors
	 * 
	 * @param image
	 *            to be labeled
	 */
	private static void labelComponents(IntImage image) {
		int componentNo = 1;
		for (int row = 0; row < image.getHeight(); ++row) {
			for (int col = 0; col < image.getWidth(); ++col) {
				// check for unlabeled cell
				if (image.get(row, col) == 1) {
					componentNo++;
					labelComponent(image, row, col, componentNo);
				} // end if
			} // end for
		} // end for
	} // end labelComponents

	/**
	 * This method sets the current cell with new component number, finds its
	 * neighbors and continue labeling until all neighbors gets new component
	 * value
	 * 
	 * @param image
	 *            to be labeled
	 * @param row
	 *            number of the current cell
	 * @param col
	 *            number of the current cell
	 * @param componentNo
	 *            current number to be set
	 */
	private static void labelComponent(IntImage image, int row, int col,
			int componentNo) {
		image.set(row, col, componentNo);

		QueueInterface<IntPair> queue = new ListQueue<>();
		queue.add(new IntPair(row, col));

		// iterate until all the adjacent cells are labeled
		while (!queue.isEmpty()) {
			IntPair current = queue.remove();
			for (IntPair pair : getNeighbors(image, current)) {
				if (image.get(pair.row, pair.col) == 1) {
					image.set(pair.row, pair.col, componentNo);
					queue.add(pair);
				} // end if
			} // end for
		} // end while
	} // end labelComponent

	/**
	 * This method reads the image matrix from console and populates pixels in
	 * {@code IntImage}
	 * 
	 * @return @code {@link IntImage}
	 */
	private static IntImage readImage() {
		// create variables
		int numRows, numCols;
		IntImage result;

		// get size of image
		System.out.println("How many rows and columns does the board have?");
		// System.out.print();
		numRows = readPositiveNumber("\tNumber of rows: ");
		// System.out.print();
		numCols = readPositiveNumber("\tNumber of columns: ");
		pause();

		// create image
		result = new IntImage(numRows, numCols);

		// read in the image
		System.out.println("Create the board by typing " + numRows
				+ " lines with " + numCols + " numbers on each line.\n"
				+ "(The numbers should be zeroes and ones only!)");
		if (result.load(scanner)) {
			System.out.println("\nThank you.  The image is complete.\n");
			pause();
		} else {
			System.out.println("\nI'm sorry, that wasn't right!\n"
					+ "Please try running this program again.\n");
			pause();
			System.exit(1);
		}

		// return loaded image
		return result;
	}

	/**
	 * This method seeks user inputs and validates it
	 * 
	 * @return validated user input
	 */
	private static int readPositiveNumber(String prompt) {
		int num = 0;
		do {
			System.out.print(prompt);
			while (!scanner.hasNextInt()) {
				System.out.print("Invalid input! " + prompt);
				scanner.next();
			}
			num = scanner.nextInt();
		} while (num < 1);
		return num;
	}

	/**
	 * the method stops the program execution and wait for user to press the
	 * enter key
	 */
	private static void pause() {
		System.out.print("\n...press enter...");
		scanner.nextLine();
		System.out.println();
	} // end pause

	/**
	 * prepares the welcome message and displays it
	 */
	private static void displayIntroduction() {
		StringBuilder sb = new StringBuilder();
		sb.append("Image Component Labeler").append("\n")
				.append("-----------------------").append("\n\n")
				.append("This program labels all the connected components"
						+ " of a 0-1 image (a dot-matrix image).")
				.append("\n\n").append("IntImage class by Mark Young")
				.append("\n")
				.append("ComponentLabeler by Khalid Alzhrani (A00361007)")
				.append("\n");
		System.out.println(sb.toString());
		pause();
	} // end displayHeading

	/**
	 * Get a list of all the current node's neighbours
	 * 
	 * @param image
	 *            - the 2D matrix
	 * @param current
	 *            - current location
	 * @return list of pairs surrounding the current
	 */
	private static Iterable<IntPair> getNeighbors(IntImage image,
			IntPair current) {
		List<IntPair> nbrs = new ArrayList<>();
		int r = current.row;
		int c = current.col;

		// check north
		if (image.validAddress(r - 1, c)) {
			nbrs.add(new IntPair(r - 1, c));
		}

		// check south
		if (image.validAddress(r + 1, c)) {
			nbrs.add(new IntPair(r + 1, c));
		}

		// check east
		if (image.validAddress(r, c + 1)) {
			nbrs.add(new IntPair(r, c + 1));
		}

		// check west
		if (image.validAddress(r, c - 1)) {
			nbrs.add(new IntPair(r, c - 1));
		}

		// list is complete
		return nbrs;
	} // end getNeighbours

	// a pair of int values
	// used as a location in the image
	// encapsulated so we can put it in a queue
	private static class IntPair {

		private int row, col;

		/**
		 * create a pair - constructor
		 * 
		 * @param r
		 *            row #
		 * @param c
		 *            column #
		 */
		public IntPair(int r, int c) {
			row = r;
			col = c;
		}

		@Override
		public String toString() {
			return String.format("(%d,%d)", row, col);
		}

		@Override
		public boolean equals(Object other) {
			if (other == null || !(other instanceof IntPair)) {
				return false;
			}
			IntPair that = (IntPair) other;
			return this.row == that.row && this.col == that.col;
		}

		// generated by NetBeans
		// it likes to have this when the equals method is defined
		@Override
		public int hashCode() {
			int hash = 7;
			hash = 37 * hash + this.row;
			hash = 37 * hash + this.col;
			return hash;
		}
	} // end IntPair
} // end ComponentLabeler
