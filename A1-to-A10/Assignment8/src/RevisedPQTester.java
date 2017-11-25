/**
 * A program to test an extended PQ class.
 * 
 * @author Mark Young (A00000000)
 */
public class RevisedPQTester {

	/** the words of the poem */
	public static final String[] words = ("Old Tom Bombadil was a merry fellow "
			+ "Bright blue his jacket was " + "And his boots were yellow "
			+ "Green were his girdle " + "And his breeches all of leather "
			+ "He wore in his tall hat a swan-wing feather").split(" ");

	public static void main(String[] args) {
		PQ<String> pq1 = new PQ<>();
		loadPQ(pq1);
		System.out.println("The words in lexicographic order:");
		showPQ(pq1);

		PQ<String> pq2 = new PQ<>();
		testGrowth(pq2);

		PQ<String> pq3 = new PQ<>(String.CASE_INSENSITIVE_ORDER);
		loadPQ(pq3);
		System.out.println("The words in alphabetical order:");
		showPQ(pq3);
	}

	/**
	 * Load the words of a poem into the given PQ.
	 * 
	 * @param pq
	 *            the PQ to load the words into
	 */
	private static void loadPQ(PQ<String> pq) {
		for (int i = 0; i < words.length; ++i) {
			pq.add(words[i]);
		}

	}

	/**
	 * Empty the given PQ onto System.out in columns.
	 * 
	 * @param pq
	 *            the PQ to empty and display.
	 */
	private static void showPQ(PQ<String> pq) {
		final int NUM_PER_LINE = 4;
		int onThisLine = 0;
		while (!pq.isEmpty()) {
			if (onThisLine % NUM_PER_LINE == 0) {
				System.out.printf("%n    ");
				onThisLine = 0;
			}
			System.out.printf("%-16s", pq.remove());
			++onThisLine;
		}
		System.out.println("\n");
	}

	private static void testGrowth(PQ<String> pq) {
		final String ENTRY = "A";
		final int SIZE = 1000;
		int count = 0;

		// make sure we can fit a lot of items in
		for (int i = 0; i < SIZE; ++i) {
			if (!pq.add(ENTRY)) {
				System.err.println("Failed to add to a PQ!");
				System.exit(1);
			}
		}

		// make sure they're all there
		while (!pq.isEmpty()) {
			if (!pq.remove().equals(ENTRY)) {
				System.err.println("Removed item is not what it should be!");
				System.exit(2);
			}
			++count;
		}

		// make sure we got the right number of items out
		if (count != SIZE) {
			System.err.println("Removed wrong number of items!");
			System.exit(3);
		}
	}

}
