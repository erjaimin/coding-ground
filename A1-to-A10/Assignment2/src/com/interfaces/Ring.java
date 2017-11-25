package com.interfaces;

import com.a2.MyRingIterator;

/**
 * An interface that describes the operations on ring of players in turn-taking
 * game.
 * 
 * @author Khalid ALZHRANI
 * @version 1.0
 * 
 */
public interface Ring<T> {

	/**
	 * Adds a new player or object to this Ring
	 * 
	 * @param newEntry
	 *            to be added
	 * @return true if the newEntry is added successfully, otherwise returns
	 *         false
	 */
	public boolean add(T newEntry);

	/**
	 * Checks if this ring is empty.
	 * 
	 * @return true if the ring is empty, otherwise returns false
	 */
	public boolean isEmpty();

	/**
	 * Gets the current no. of players or objects part of this ring.
	 * 
	 * @return the integer number of entries currently in the ring.
	 */
	public int getCurrentSize();

	/**
	 * Retrieves all the entries in this bag in the form of an array.
	 * 
	 * @return A newly created array after populating all the entries in the
	 *         ring. If the ring is empty then the result array will be empty as
	 *         well.
	 */
	public T[] toArray();

	/**
	 * Gets an iterator to this ring.
	 * 
	 * @return an iterator over the entries in this ring in proper sequence.
	 */
	public MyRingIterator<T> getIterator();
}
