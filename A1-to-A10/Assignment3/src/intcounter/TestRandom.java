package intcounter;

import java.util.Arrays;

/**
 * A class to test whether various distributions are fair.
 * Uses Math.random to select at random from the distribution,
 * and an IntCounter object to track the number of times
 * each item in the distribution is selected.
 *
 * @author Mark Young (A00000000)
 */
public class TestRandom {

    /** &Chi;-squared critical value for 5 degrees of freedom, p < .99 */
    public static final double CHI_5_95 = 11.07;

    /** &Chi;-squared critical value for 5 degrees of freedom, p < .95 */
    public static final double CHI_5_99 = 15.086;

    /** number of selections in each test run */
    public static final int NUM_ROLLS = 500;

    /** distributions to test */
    private static final int[][] distributions = new int[][]{
        new int[]{0, 1, 2, 3, 4, 5},
        new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5},
        new int[]{1, 1, 1, 2, 3, 4, 5, 6},
        new int[]{20, 21, 22, 23, 24, 25}
    };

    public static void main(String[] args) {
        // introduce yourself
        System.out.println("\n"
                + "A program to test whether a distribution is uniform "
                + "by making hundreds of \nrandom selections from it.\n\n"
                + "By Mark Young (A00000000).\n"
                + "IntCounter class by " + IntCounter.getAuthorData()
                + ".\n");

        // test each distribution
        for (int i = 0; i < distributions.length; ++i) {
            testDistribution(distributions[i]);
        }
    }

    /**
     * Test one distribution.
     * <p>
     * Preconditions: <code>dist</code> contains numbers in the range
     * [min..max] where <code>max - min == 5</code>.
     * <p>
     * Postconditions: 
     *  <ul>
     *    <li> <code>dist</code> has been printed;
     *    <li> NUM_ROLLS independent selections from <code>dist</code> have
     *         been made and counted, and the counts displayed
     *    <li> the &Chi;-squared measure for the counts has been calculated
     *    <li> an estimation of whether the sample is biased has been printed
     *         together with an indication of the confidence level
     *  </ul>
     * 
     * @param dist the distribution to test
     */
    private static void testDistribution(int[] dist) {
        System.out.println("\n"
                + "Testing distribution: " + Arrays.toString(dist));
        
        // find range
        int max = findMax(dist);
        int min = findMin(dist);
        
        // check program assumptions
        assert max - min == 5;
        
        // create counters
        IntCounter ic;
        if (min == 0) {
            ic = new IntCounter(max);
        } else {
            ic = new IntCounter(min, max);
        }
        
        // select repeatedly from distribution
        for (int i = 0; i < NUM_ROLLS; ++i) {
            int value = selectFrom(dist);
            ic.count(value);
        }
        
        // show results
        System.out.println("The results of the "
                + ic.getTotal() + " selections are: "
                + Arrays.toString(ic.toArray()));
        
        // calculate chi-squared value
        double expected = (double)NUM_ROLLS / (double)(max - min + 1);
        double sum = 0.0;
        for (int i = min; i <= max; ++i) {
            int got = ic.getCount(i);
            double error = (got - expected);
            double errorSquared = error * error;
            sum += errorSquared / expected;
        }
        System.out.println("The chi-squared measure for this data is "
                + sum + ".");
        
        // report result
        if (sum > CHI_5_99) {
            System.out.println("BIASED (p > .99)");
        } else if (sum > CHI_5_95) {
            System.out.println("BIASED (p > .95)");
        } else {
            System.out.println("FAIR (p < .95)");
        }
        System.out.println();
    }

    /**
     * Find the largest value in the given array.
     * <p>
     * Preconditions: <code>dist</code> has at least one element
     * <p>
     * Postconditions: the value returned is equal to the largest value in 
     * <code>dist</code>
     *
     * @param dist the array to find the maximum of
     * @return the maximum value from that array
     */
    private static int findMax(int[] dist) {
        int max = dist[0];
        for (int i = 1; i < dist.length; ++i) {
            max = Math.max(max, dist[i]);
        }
        return max;
    }

    /**
     * Find the smallest value in the given array.
     * <p>
     * Preconditions: <code>dist</code> has at least one element
     * <p>
     * Postconditions: the value returned is equal to the smallest value in 
     * <code>dist</code>
     *
     * @param dist the array to find the minimum of
     * @return the minimum value from that array
     */
    private static int findMin(int[] dist) {
        int min = dist[0];
        for (int i = 1; i < dist.length; ++i) {
            min = Math.min(min, dist[i]);
        }
        return min;
    }

    /**
     * Select one value at random (uniformly) from the given array.
     *
     * @param dist the array to choose from
     * @return one element of the array, chosen at random (uniformly)
     */
    private static int selectFrom(int[] dist) {
        int roll = (int)(dist.length * Math.random());
        return dist[roll];
    }

}
