package a09;

/**
 * A Priority Queue class.
 * 
 * @author Mark Young (A00000000)
 */
public class PQ<T extends Comparable<? super T>> {
    
    // ---------- Counter IVs and Methods ------------------------------ //
    private int opCount = 0;
    
    /**
     * Return the current operation count without changing it.
     * 
     * @return value of operation counter.
     */
    public int getOpCount() {
        return opCount;
    }
    
    /**
     * Set the operation count back to zero.
     */
    public void resetOpCount() {
        opCount = 0;
    }
    
    /**
     * Get the current operation count before setting it to zero.
     * 
     * @return the operation count before it was reset to zero.
     */
    public int getAndResetOpCount() {
        int result = opCount;
        opCount = 0;
        return result;
    }
    
    // ---------- Previous Implementation of PQ ------------------------- //

    public static final int DEFAULT_CAPACITY = 10;

    private T[] list;
    private int numInQueue;
    
    /**
     * Create a PQ with the given capacity.
     * 
     * @param capacity the requested capacity of the PQ.
     */
    public PQ(int capacity) {
        // make array one bigger because we don't use location 0.
        list = (T[])new Comparable[capacity+1];
        numInQueue = 0;
    }
    
    /**
     * Create a PQ with a default capacity.
     */
    public PQ() {
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * Add newElement to this PQ
     * 
     * @param newElement the element to add
     * @return true if successful; false otherwise
     */
    public boolean add(T newElement) {
    	++opCount; // assignment 
        int posn = numInQueue + 1;
        ++opCount; // comparison: posn == list.length
        if (posn == list.length) {
            return false;
        }
        ++opCount; // comparison
        while (posn > 1) {
        	++opCount; // assignment
            T other = list[posn/2];
            ++opCount; // comparison: other.compareTo(newElement) > 0
            if (other.compareTo(newElement) > 0) {
            	++opCount; // assignment operation
                list[posn] = other;
                ++opCount; // division
                posn = posn/2;
            } else {
                break;
            }
            ++opCount; // next comparison operation
        }
        ++opCount; // assignment
        list[posn] = newElement;
        ++opCount; // increment operation
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
    	++opCount; // assignment
        T result = list[1];
        ++opCount; // assignment
        T other = list[numInQueue];
        ++opCount; // assignment
        int posn = 1;
        ++opCount; // comparison: numInQueue > 2 * posn
        while (numInQueue > 2 * posn) {
        	++opCount; // assignment
            int small = smallerChild(posn);
            ++opCount; // comparison
            if (list[small].compareTo(other) < 0) {
            	++opCount; // assignment
                list[posn] = list[small];
                ++opCount; // assignment
                posn = small;
            } else {
                break;
            }
            ++opCount; // next comparison operation
        }
        ++opCount; // assignment
        list[posn] = other;
        ++opCount; // decrement operation
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
    	++opCount; // assignment
        int c1 = posn * 2;
        ++opCount; // assignment
        int c2 = c1 + 1;
        ++opCount; // assignment
        if (c2 >= numInQueue) {
            return c1;
        } else if (list[c2].compareTo(list[c1]) < 0) {
            return c2;
        } else {
            return c1;
        }
    }
}
