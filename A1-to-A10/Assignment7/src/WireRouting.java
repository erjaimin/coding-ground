import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A program using a Queue to find the shortest path. In this example the path
 * is on a matrix of 0s and 1s representing a circuit board. (The 1s represent
 * used space and the 0s represent open space.)
 *
 * @author Mark Young (A00000000)
 */
public class WireRouting {

    private static final Scanner KBD = new Scanner(System.in);
    private static boolean tracing = false;

    public static void main(String[] args) {
        // create variables
        IntImage matrix;
        IntPair start, end;

        // introduce yourself
        printIntroduction();

        // read image
        matrix = readImage();

        // read start and end locations
        start = readLocation("Where does the wire start? ");
        end = readLocation("Where does the wire end? ");
        pause();

        // place the wire
        routeWire(matrix, start, end);

        // show the revised image
        System.out.println("Here is the revised image:");
        matrix.print();
        pause();
    }

    // introduce yourself and pause
    private static void printIntroduction() {
        System.out.println("\n"
                + "Wire Routing Program\n"
                + "--------------------\n\n"
                + "This program simulates placing a wire on a circuit-board.\n"
                + "The board is represented by a 0-1 matrix "
                + "that you need to\nenter.\n");
        pause();
    }

    // get image size and values
    private static IntImage readImage() {
        // create variables
        int numRows, numCols;
        IntImage result;

        // get size of image
        System.out.println("How many rows and columns does the board have?");
        System.out.print("\tNumber of rows: ");
        numRows = readPositiveNumber();
        System.out.print("\tNumber of columns: ");
        numCols = readPositiveNumber();
        pause();

        // create image
        result = new IntImage(numRows, numCols);

        // read in the image
        System.out.println("Create the board by typing " + numRows
                + " lines with " + numCols + " numbers on each line.\n"
                + "(The numbers should be zeroes and ones only!)");
        if (result.load(KBD)) {
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

    // read a row and column
    private static IntPair readLocation(String prompt) {
        int row, col;

        System.out.print(prompt);
        row = KBD.nextInt();
        col = KBD.nextInt();
        KBD.nextLine();

        return new IntPair(row, col);
    }

    // place the wire on the shortest path
    private static void routeWire(IntImage matrix, IntPair start, IntPair end) {
        labelDistances(matrix, start, end);
        findRouteBack(matrix, start, end);
    }

    // read a number greater than zero
    private static int readPositiveNumber() {
        int num = KBD.nextInt();
        KBD.nextLine();
        while (num < 1) {
            System.out.print("Please enter a POSITIVE number: ");
            num = KBD.nextInt();
            KBD.nextLine();
        }
        return num;
    }

    // use a queue to label how far each accessible point in the matrix is
    // from the start position
    private static void labelDistances(IntImage matrix, 
            IntPair start, IntPair end) {
        // create queue and put start location into it
        QueueInterface<IntPair> q = new ListQueue<>();
        matrix.set(start.row, start.col, 2);
        q.add(start);
        
        // while there are still places to look
        while (!q.isEmpty()) {
            // print a trace if required
            if (tracing) {
                System.out.println("\nTracing distance labelling:");
                System.out.println("Queue = " + q);
                matrix.print();
                pause();
            }
            
            // if we've reached the end, break out of the loop
            if (q.front().equals(end)) {
                break;
            }
            
            // find and label the neighbours of the first position in the queue
            labelAndAddNeighbours(q, matrix);
            q.remove();
        }
        
        // the end node should be the front element of the queue
        // if it isn't, we couldn't find a path
        if (q.isEmpty()) {
            throw new IllegalStateException("No path from " + start
                    + " to " + end);
        }
    }

    // retrace steps from end back to start
    private static void findRouteBack(IntImage matrix, 
            IntPair start, IntPair end) {
        // start at the end and work your way back
        IntPair current = end;
        while (!current.equals(start)) {
            // get the current location's label
            int label = matrix.get(current.row, current.col);
            
            // reset its label to 1
            matrix.set(current.row, current.col, 1);
            
            // print trace message, if appropriate
            if (tracing) {
                System.out.println("\nTracing route back:");
                matrix.print();
                pause();
            }
            
            // move to a neighbour with a label one less than the current
            current = findNeighbour(matrix, current, label - 1);
        }
        
        // label the start node with a 1, too
        matrix.set(start.row, start.col, 1);
        
        // zero out all the other locations we visited
        zeroNon1Cells(matrix);
    }

    // process all the neighbours of q's front element
    private static void labelAndAddNeighbours(QueueInterface<IntPair> q, 
            IntImage matrix) {
        // get the front element and add one to its label
        IntPair current = q.front();
        int value = 1 + matrix.get(current.row, current.col);
        
        // for each neighbour
        for (IntPair nbr : neighbours(matrix, current)) {
            // if its current label is zero
            if (matrix.get(nbr.row, nbr.col) == 0) {
                // label it and add it to the queue
                matrix.set(nbr.row, nbr.col, value);
                q.add(nbr);
            }
        }
    }

    // get a list of all the current node's neighbours
    private static Iterable<IntPair> neighbours(IntImage matrix, 
            IntPair current) {
        List<IntPair> nbrs = new ArrayList<>();
        int r = current.row;
        int c = current.col;

        // check north
        if (matrix.validAddress(r - 1, c)) {
            nbrs.add(new IntPair(r - 1, c));
        }

        // check south
        if (matrix.validAddress(r + 1, c)) {
            nbrs.add(new IntPair(r + 1, c));
        }

        // check east
        if (matrix.validAddress(r, c + 1)) {
            nbrs.add(new IntPair(r, c + 1));
        }

        // check west
        if (matrix.validAddress(r, c - 1)) {
            nbrs.add(new IntPair(r, c - 1));
        }

        // list is complete
        return nbrs;
    }

    // find a neighbour with the given label
    private static IntPair findNeighbour(IntImage matrix, 
            IntPair current, int label) {
        for (IntPair nbr : neighbours(matrix, current)) {
            if (matrix.get(nbr.row, nbr.col) == label) {
                return nbr;
            }
        }
        return null;
    }

    // reset all labeled (2 or more) matrix cells to 0
    private static void zeroNon1Cells(IntImage matrix) {
        for (int r = 0; r < matrix.getHeight(); ++r) {
            for (int c = 0; c < matrix.getWidth(); ++c) {
                if (matrix.get(r, c) > 1) {
                    matrix.set(r, c, 0);
                }
            }
        }
    }

    // prompt the user and wait for them to press enter
    private static void pause() {
        System.out.print("\n...press enter...");
        KBD.nextLine();
        System.out.println();
    }

    // a pair of int values
    // used as a location in the matrix
    // encapsulated so we can put it in a queue
    private static class IntPair {

        int row, col;

        // create a pair
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
    }

}
