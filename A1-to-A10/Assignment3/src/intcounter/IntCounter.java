package intcounter;

/**
 * This class is responsible for counting the occurrences of each number and also 
 * the total number of counts and related operations
 * @author Khalid ALZHRANI (A00361007)
 *
 */
public class IntCounter {

	/**
	 * data members
	 */
	private int min;
	private int max;
	private int[] counts;
	private int totalCount;

	/**
	 * creates an instance of <code>IntCounter</code>
	 * @param max number present in distribution
	 */
	public IntCounter(int max) {
		this.max = max;
		this.counts = new int[this.max - this.min + 1];
	}

	/**
	 * creates an instance of <code>IntCounter</code>
	 * @param min minimum number present in distribution
	 * @param max maximum number present in distribution
	 */
	public IntCounter(int min, int max) {
		this.min = min;
		this.max = max;
		this.counts = new int[this.max - this.min + 1];
	}

	/**
	 * counts the occurrences of a given value and also total of all counts
	 * @param value to be counted
	 */
	public void count(int value) {
		if(value >= this.min && value <= this.max){
			this.counts[value - this.min]++;
			this.totalCount++;
		}
	}

	/**
	 * Returns the total of all the counts
	 * @return total count
	 */
	public int getTotal() {
		return this.totalCount;
	}

	/**
	 * Returns the individual count of a number 
	 * @param value whose count is needed
	 * @return the occurrences
	 */
	public int getCount(int value) {
		if(value >= this.min && value <= this.max){
			return this.counts[value - this.min];
		}
		return 0;
	}

	/**
	 * Returns an array holding the counts for each number
	 * @return array of counts
	 */
	public int[] toArray() {
		return this.counts;
	}

	/**
	 * Returns the author data of class <code>IntCounter</code>
	 * @return author details
	 */
	public static String getAuthorData() {
		return "Khalid ALZHRANI (A00361007)";
	}
}
