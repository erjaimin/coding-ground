
import java.util.Arrays;

/**
 * A program to count operations in Shaker sort.
 * 
 * @author Khalid Alzhrani (A00361007)
 */
public class ShakerSort {

    private static boolean tracing;

    private static int numAsgn = 0;
    private static int numComp = 0;
    private static int HOW_MANY = 10000;

    public static void main(String[] args) {
        System.out.println("\n\n"
                + "Shaker Sort\n"
                + "===========\n");

        // sort Strings
        sortNames(new String[]{"A", "B", "C", "D"});
        sortNames(new String[]{"B", "C", "A", "D", "E"});
        sortNames(new String[]{"A", "G", "B", "C", "E", "F", "D"});

        // sort integers
        sortNumbers();

        System.out.println();
    }

    /**
     * Sort an array of Strings.
     * 
     * @param names the array to sort.
     */
    public static void sortNames(String[] names) {
        // reset counts and turn tracing on
        resetCounts();
        tracing = true;

        // say what we're doing
        System.out.println("\n"
                + "Sorting Strings\n"
                + "---------------\n");

        // show unsorted list
        System.out.println("Before sorting:\n\t" + Arrays.toString(names));
        
        // sort
        shakerSort(names);

        // show sorted list
        System.out.println("After sorting:\n\t" + Arrays.toString(names));

        // report operation counts
        System.out.printf("To sort %,d names "
                + "took %,d assignments "
                + "and %,d comparisons,\n"
                + "for a total of %,d operations.%n%n",
                names.length, numAsgn, numComp, numAsgn + numComp);
    }

    /**
     * Generate and sort an array of random numbers.
     */
    public static void sortNumbers() {
        resetCounts();
        tracing = false;

        // generate some random numbers
        Integer[] numbers = randomNumbers(HOW_MANY, 1, HOW_MANY);

        // report what we're doing
        System.out.println("\n"
                + "Sorting Numbers\n"
                + "---------------\n");
        
        System.out.printf("To sort %,d numbers "
                + "took %,d assignments "
                + "and %,d comparisons,\n"
                + "for a total of %,d operations.%n%n",
                HOW_MANY, numAsgn, numComp, numAsgn + numComp);

        // sort the numbers
        shakerSort(numbers);
        
        // print the results
        System.out.printf("To sort %,d numbers "
                + "took %,d assignments "
                + "and %,d comparisons,\n"
                + "for a total of %,d operations.%n%n",
                HOW_MANY, numAsgn, numComp, numAsgn + numComp);

        // check that the list is ACTUALLY sorted
        for (int i = 0; i < numbers.length - 1; ++i) {
            if (numbers[i].compareTo(numbers[i + 1]) > 0) {
                System.out.println("But it's not sorted!!!");
                break;
            }
        }
    }

    /**
     * Reset our operation counts to zero.
     */
    public static void resetCounts() {
        numAsgn = 0;
        numComp = 0;
    }

    /**
     * Generate an array of random numbers.
     * 
     * @param howMany how many numbers will be in the array
     * @param lo    the lowest value for numbers in the array (inclusive)
     * @param hi    the highest value for numbers in the array (exclusive)
     * @return an array of howMany numbers in the range [lo, hi)
     */
    public static Integer[] randomNumbers(int howMany, int lo, int hi) {
        int range = hi - lo;
        Integer[] result = new Integer[howMany];
        for (int i = 0; i < howMany; ++i) {
            result[i] = (int) (lo + range * Math.random());
        }
        return result;
    }

    /**
     * Sort an array using shaker sort.
     * 
     * @param <T> the base type of the array
     * @param a the array to sort
     */
    public static <T extends Comparable<? super T>>
            void shakerSort(T[] a) {
    	int lastSwapPos = 0;
    	int lastUnsorted = a.length - 1;
    	while(lastUnsorted > 0){
        	lastSwapPos=0;
            for (int j = 0; j < lastUnsorted; ++j) {
                numComp++;
                if (a[j].compareTo(a[j + 1]) > 0) {
                    T temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                    lastSwapPos = j;
                    numAsgn += 3;
                }
            }
            lastUnsorted = lastSwapPos;
            for (int j = lastUnsorted; j > lastSwapPos; j--) {
                numComp++;
                if (a[j].compareTo(a[j - 1]) < 0) {
                    T temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                    lastSwapPos = j;
                    numAsgn += 3;
                }
            }
            if (tracing) {
                System.out.println("one more bubbled up: " + Arrays.toString(a));
            }
        }
    }
}
