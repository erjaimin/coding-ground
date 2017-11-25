package com.interfaces;

/**
 * An interface the describes the operations of an iterator of {@link Ring}
 * 
 * @author Khalid ALZHRANI
 * @version 1.0
 *
 */
public interface RingIterator<T> {

	/**
	 * Checks if the iterator has more entries.
	 * 
	 * @return true if the next entry is present, otherwise false
	 */
	public boolean hasNext();

	/**
	 * Retrieves the next entry in the ring. The user need to make sure that
	 * Ring is not empty before invoking this method.
	 * 
	 * @return next entry
	 */
	public T next();

	/**
	 * Removes the current entry from the ring.
	 * 
	 * @return true if the deletion went successfully, otherwise returns false.
	 */
	public boolean eliminate();
}
