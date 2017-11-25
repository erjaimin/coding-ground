package a09;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A program to count operations in a PQ.
 *
 * @author Mark Young (A00000000)
 */
public class A09 {

    public static final int MAX_SIZE = 32;
    public static final int NUM_TRIALS = 10;
    public static final Scanner KBD = new Scanner(System.in);

    public static void main(String[] args) {
        // Create variables
        List<Integer> addCounts, removeCounts;
        PQ<Integer> pq = new PQ<>(MAX_SIZE);

        // get operation counts for adding to pq
        addCounts = fillPQ(pq);

        // get operation counts for deleting from PQ
        removeCounts = emptyPQ(pq);

        // show the results
        showResults(addCounts, removeCounts);
    }

    /**
     * Add MAX_SIZE elements to the given PQ, returning the counts of how many
     * operations for each insertion.
     *
     * @param pq the PQ to fill up.
     * @return a List where the Nth element is the number of operations it took
     *         to insert into a PQ of size N.
     */
    private static List<Integer> fillPQ(PQ<Integer> pq) {
        List<Integer> counts = new ArrayList<>();
        int numToInsert = Integer.MAX_VALUE;
        for (int size = 0; size < MAX_SIZE; ++size) {
            // add a new smallest element (worst case for insertion)
            pq.resetOpCount();
            pq.add(numToInsert);
            --numToInsert;

            // add the current operation count to the counts List
            counts.add(pq.getOpCount());
        }

        return counts;
    }

    /**
     * Remove all elements from the given PQ, returning the counts
     *
     * @param pq
     * @return a List where the Nth element (for N > 0) is the number of
     *         operations it took to delete an element from a PQ of size N. (The
     *         0th element is arbitrary, since we can't delete from a PQ of size
     *         0.)
     */
    private static List<Integer> emptyPQ(PQ<Integer> pq) {
        List<Integer> counts = new ArrayList<>();
        while (!pq.isEmpty()) {
            pq.resetOpCount();
            pq.remove();
            counts.add(0, pq.getOpCount());
        }
        counts.add(0, -1);
        return counts;
    }

    /**
     * Print out the counts of operations in a nice table.
     *
     * @param addCounts    the counts for adding to a PQ
     * @param removeCounts the counts for removing from a PQ
     */
    private static void showResults(List<Integer> addCounts, List<Integer> removeCounts) {
        System.out.println("\n\n"
                + "Operation Counts for Adding to/Removing from "
                + "a PQ of size N.\n"
                + "============================================="
                + "===============\n");
        System.out.printf("%8s%10s%10s%n",
                "N", "Adding", "Removing");
        System.out.printf("%8s%10s%10s%n",
                "------", "------", "--------");
        System.out.printf("%8d%10d%10s%n",
                0, addCounts.get(0), "n/a");
        for (int i = 1; i < addCounts.size(); ++i) {
            System.out.printf("%8d%,10d%,10d%n",
                    i, addCounts.get(i), removeCounts.get(i));
        }
        System.out.println();
    }

}
