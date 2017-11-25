import java.util.Arrays;
import java.util.Scanner;

/**
 * This class performs tests on the extensions to the LinkedBag class.
 *
 * @author Charles Hoot
 * @author Mark Young (A00000000)
 * @version 5.0
 */
public class BagExtensionsTest {

    public static final Scanner in = new Scanner(System.in);

    public static final String[] empty = new String[]{};
    public static final String[] oneElement = new String[]{"A"};
    public static final String[] twoElements = new String[]{"A", "B"};
    public static final String[] twoSame = new String[]{"Larry", "Larry"};
    public static final String[] threeElements = new String[]{"A", "B", "C"};
    public static final String[] threeSame = new String[]{"Larry", "Larry", "Larry"};
    public static final String[] moreElements = new String[]{"Larry", "Curly", "Moe", "Shemp"};
    public static final String[] someDuplicates = new String[]{"A", "B", "C", "C", 
                                                "D", "C", "E", "D"};
    public static final String[] allDuplicated = new String[]{"A", "A", "A", "B", "C", 
                                                "A", "B", "C", "C"};
    public static final String[] tricky = new String[]{"[A]", "(B)", "{C}", "[[D[[C"};

    public static void main(String args[]) {
        checkToString();
        checkExpunge();
        checkMoveTo();
    }

    public static void checkToString() {
        System.out.println("TESTING toString");

        testToString("Empty bag", empty);
        pause();
        testToString("One element", oneElement);
        pause();
        testToString("Two elements", twoElements);
        pause();
        testToString("More elements", moreElements);
        pause();
        testToString("Some duplicates", someDuplicates);
        pause();
        testToString("All duplicates", allDuplicated);
        pause();
        testToString("Weirdness", tricky);
        pause();
    }

    public static void checkExpunge() {
        System.out.println("TESTING expunge");

        testExpunge("Empty bag", empty, "A");
        testExpunge("One element (yes)", oneElement, "A");
        testExpunge("One element (no)", oneElement, "B");
        pause();
        testExpunge("Two elements (one end)", twoElements, "A");
        testExpunge("Two elements (other end)", twoElements, "B");
        testExpunge("Two elements (none)", twoElements, "C");
        pause();
        testExpunge("Two the same (yes)", twoSame, "Larry");
        testExpunge("Two the same (no)", twoSame, "Curly");
        pause();
        testExpunge("Three the same (yes)", threeSame, "Larry");
        testExpunge("Three the same (no)", threeSame, "Moe");
        pause();
        testExpunge("No duplicates (one end)", moreElements, "Larry");
        testExpunge("No duplicates (middle)", moreElements, "Moe");
        testExpunge("No duplicates (other end)", moreElements, "Shemp");
        pause();
        testExpunge("Some duplicates (one end)", someDuplicates, "A");
        testExpunge("Some duplicates (middle)", someDuplicates, "C");
        testExpunge("Some duplicates (other end)", someDuplicates, "D");
        pause();
        testExpunge("All duplicates (one end)", allDuplicated, "A");
        testExpunge("All duplicates (middle)", allDuplicated, "B");
        testExpunge("All duplicates (other end)", allDuplicated, "C");
        pause();

        System.out.println();
    }

    public static void checkMoveTo() {
        System.out.println("TESTING removeDuplicates");

        testMoveTo("Empty -> Empty", empty, empty);
        testMoveTo("Empty -> non-Empty", empty, threeElements);
        pause();
        testMoveTo("non-Empty -> Empty", twoElements, empty);
        testMoveTo("non-Empty -> non-Empty", allDuplicated, threeElements);
        pause();

        System.out.println();
    }

    private static LinkedBag<String> toBag(String[] arr) {
        LinkedBag<String> bag = new LinkedBag<>();
        for (String s : arr) {
            bag.add(s);
        }
        return bag;
    }

    private static void testToString(String description, String[] arr) {
        LinkedBag<String> bag = toBag(arr);

        String result = bag.toString();
        String trimmed = result.trim();
        boolean extraSpaces = !result.equals(trimmed);
        boolean startsWithParen = trimmed.startsWith("(");
        boolean endsWithParen = trimmed.endsWith(")");
        boolean commasOK = true;
        boolean commaCountOK = true;
        boolean spacesOK = true;
        boolean allIn = true;

        // get positions of elements (and remember which is last)
        int[] positions = new int[arr.length];
        int lastPosition = 0;
        for (int i = 0; i < arr.length; ++i) {
            positions[i] = trimmed.indexOf(arr[i]);
            if (positions[i] < 0) {
                allIn = false;
            }
            lastPosition = Math.max(lastPosition, positions[i]);
        }

        // check if all but last followed by ", "
        for (int i = 0; i < positions.length; ++i) {
            if (positions[i] >= 0 && positions[i] < lastPosition) {
                int endOfWord = positions[i] + arr[i].length();
                int commaLocation = trimmed.indexOf(",", endOfWord);
                commasOK &= commaLocation == endOfWord;
                int spaceLocation = trimmed.indexOf(" ", endOfWord);
                spacesOK &= spaceLocation == endOfWord
                        || spaceLocation == endOfWord + 1;
            }
        }

        commaCountOK = commasIn(result) == Math.max(0, (arr.length - 1));

        // report results
        System.out.println(description + ": "
                + Arrays.toString(arr) + " -> \""
                + result + "\""
                + "\n\tAll elements included: "
                + (allIn ? "PASS" : "FAIL ***")
                + "\n\tNo extra spaces:       "
                + (extraSpaces ? "FAIL ***" : "PASS")
                + "\n\tStarts with (:         "
                + (startsWithParen ? "PASS" : "FAIL ***")
                + "\n\tEnds with ):           "
                + (endsWithParen ? "PASS" : "FAIL ***")
                + "\n\tCommas correct:        "
                + (commasOK ? "PASS" : "FAIL ***")
                + "\n\tRight # commas:        "
                + (commaCountOK ? "PASS" : "FAIL ***")
                + "\n\tSpaces correct:        "
                + (spacesOK ? "PASS" : "FAIL ***")
                + "\n");
        
        System.out.println();
    }

    private static void testExpunge(String desc, String[] arr, String a) {
        LinkedBag<String> bag = toBag(arr);
        boolean allRemoved = true;
        boolean restRemain = true;
        
        System.out.print(desc + ": " + bag.toString() + " - " + a + " -> ");
        bag.expunge(a);
        System.out.println(bag.toString());
        
        // everything other than a should have same frequency in bag as in arr
        // a should have frequency 0 in bag
        for (int i = 0; i < arr.length; ++i) {
            if (a.equals(arr[i])) {
                if (bag.getFrequencyOf(a) > 0) {
                    allRemoved = false;
                }
            } else if (bag.getFrequencyOf(arr[i]) != count(arr[i], arr)) {
                restRemain = false;
            }
        }
        
        // report results
        System.out.printf("%8s%-25s %s %n", 
                "", 
                "All \"" + a + "\" expunged:", 
                (allRemoved ? "PASS" : "FAIL ***"));
        System.out.printf("%8s%-25s %s %n", 
                "",
                "No others removed:",
                (restRemain ? "PASS" : "FAIL ***"));
        System.out.println();
    }

    private static void testMoveTo(String desc, String[] a1, String[] a2) {
        LinkedBag<String> bag1 = toBag(a1);
        LinkedBag<String> bag2 = toBag(a2);
        boolean allItShould = true;

        System.out.println(desc + ": " + bag1.toString() + " ==> " 
                + bag2.toString());
        bag1.moveTo(bag2);
        System.out.println(desc + ": " + bag1.toString() + " AND " 
                + bag2.toString());

        // frequency of any item in bag2 should be total of counts in a1 and a2
        Object[] bag1arr = bag1.toArray();
        Object[] bag2arr = bag2.toArray();

        for (int i = 0; i < bag2arr.length; ++i) {
            Object si = bag2arr[i];
            if (count(si, bag2arr) != count(si, a1) + count(si, a2)) {
                allItShould = false;
            }
        }

        System.out.printf("%8s%-25s %s %n",
                "",
                "bag1 now empty: ",
                (bag1.isEmpty() ? "PASS" : "FAIL ***"));
        System.out.printf("%8s%-25s %s %n",
                "",
                "bag2 has all it should:",
                (allItShould ? "PASS" : "FAIL ***"));
    }
    

    private static <T> int count(Object a, T[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i].equals(a)) {
                ++count;
            }
        }
        return count;
    }

    private static int commasIn(String r) {
        int count = 0;
        for (int i = 0; i < r.length(); ++i) {
            if (r.charAt(i) == ',') {
                ++count;
            }
        }
        return count;
    }

    private static void pause() {
        System.out.print("\nPress enter...");
        in.nextLine();
        System.out.println();
    }

}
