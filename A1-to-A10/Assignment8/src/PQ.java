import java.util.Comparator;

/**
 * A Priority Queue class.
 * 
 * @author Mark Young (A00000000)
 */
public class PQ<T extends Comparable<? super T>> {

	public static final int DEFAULT_CAPACITY = 10;
	public static final int MAX_CAPACITY = 10000;

	private T[] list;
	private int numInQueue;
	private Comparator<T> comparator;

	/**
	 * creates an instance of PQ
	 */
	public PQ() {
		this((t1, t2) -> {
			return t1.compareTo(t2);
		});
	}

	/**
	 * creates an instance of PQ
	 * @param comparator 
	 *         - sorting order
	 */
	public PQ(Comparator<T> comparator) {
		this.comparator = comparator;
		list = (T[]) new Comparable[DEFAULT_CAPACITY];
		numInQueue = 0;
	}

	/**
	 * Add newElement to this PQ
	 * 
	 * @param newElement
	 *            the element to add
	 * @return true if successful; false otherwise
	 */
	public boolean add(T newElement) {
		int posn = numInQueue + 1;
		// expand when list is full
		if (posn == list.length) {
			int newSize = ((posn * 2) > MAX_CAPACITY && posn != MAX_CAPACITY)
					? MAX_CAPACITY : posn * 2;
			if (newSize <= MAX_CAPACITY) {
				// creating larger array to accommodate new items
				T[] temp = (T[]) new Comparable[newSize];
				System.arraycopy(list, 0, temp, 0, list.length);
				list = temp;
			} else {
				return false;
			}
		}
		while (posn > 1) {
			T other = list[posn / 2];
			if (comparator.compare(other, newElement) > 0) {
				list[posn] = other;
				posn = posn / 2;
			} else {
				break;
			}
		}
		list[posn] = newElement;
		++numInQueue;
		return true;
	}

	/**
	 * Whether this PQ is empty.
	 * 
	 * @return whether this PQ is empty.
	 */
	public boolean isEmpty() {
		return numInQueue == 0;
	}

	/**
	 * Remove the highest-priority item from this PQ.
	 * 
	 * @return the highest-priority item from this PQ
	 */
	public T remove() {
		T result = list[1];
		T other = list[numInQueue];
		int posn = 1;
		while (numInQueue > 2 * posn) {
			int small = smallerChild(posn);
			if (comparator.compare(list[small], other) < 0) {
				list[posn] = list[small];
				posn = small;
			} else {
				break;
			}
		}
		list[posn] = other;
		--numInQueue;
		return result;
	}

	/**
	 * Returns smaller child of given position
	 * @param posn 
	 *       - current position
	 * @return
	 */
	private int smallerChild(int posn) {
		int c1 = posn * 2;
		int c2 = c1 + 1;
		if (c2 >= numInQueue) {
			return c1;
		} else if (comparator.compare(list[c2], list[c1]) < 0) {
			return c2;
		} else {
			return c1;
		}
	}
}
