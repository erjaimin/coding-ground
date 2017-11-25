package com.a2;

import com.interfaces.Ring;

/**
 * An implementation of the {@code Ring} interface that adds the Players to the ring
 * for dice-rolling game.    
 * <p>
 * To simplify the implementation, any object is allowed in the MyRing.
 * <p>
 * @author Khalid ALZHRANI
 * @version 1.0
 */
public class MyRing<T> implements Ring<T> {
	
	/**
	 * This method allows a player to seat at the table/add a player to ring
	 * <p>
     * Preconditions: newEntry not null
     * <p>
     * Postconditions: none
	 * @return true if the addition went successful, otherwise returns false
	 * @throws UnsupportedOperationException
	 */
	@Override
	public boolean add(T newEntry) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * This method checks if the ring is empty or have some players
	 * <p>
     * Preconditions: none
     * <p>
     * Postconditions: none
	 * @return true if the ring is empty otherwise false
	 * @throws UnsupportedOperationException
	 */
	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * This method returns the count of players currently present in this ring
	 * <p>
     * Preconditions: none
     * <p>
     * Postconditions: none
	 * @return the number of players part of the ring
	 * @throws UnsupportedOperationException
	 */
	@Override
	public int getCurrentSize() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * This method allocates new array of the same size returned by {@link #getCurrentSize()} 
	 * and copies all the entries from the ring.
	 * <p>
     * Preconditions: none
     * <p>
     * Postconditions: none
	 * @return An array representation of the ring; return empty array if no players exist.
	 * @throws UnsupportedOperationException
	 */
	@Override
	public T[] toArray() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Gets an iterator for this ring to iterate over the players 
	 * <p>
     * Preconditions: ring not null
     * <p>
     * Postconditions: none
	 * @return {@link MyRingIterator}
	 * @throws UnsupportedOperationException
	 */
	@Override
	public MyRingIterator<T> getIterator() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
