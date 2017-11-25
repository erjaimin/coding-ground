package com.a2;

import com.interfaces.RingIterator;

/**
 * An implementation of the {@code RingIterator} interface that adds the Players
 * to the ring for dice-rolling game.
 * <p>
 * To simplify the implementation, any object is allowed in the MyRing.
 * <p>
 * 
 * @author Khalid ALZHRANI
 * @version 1.0
 *
 */
public class MyRingIterator<T> implements RingIterator<T> {

	/**
	 * Gets the next entry/player present in the ring.
	 * 
	 * @return the next entry in the ring
	 *         <p>
	 *         Preconditions: ring must not be empty
	 * @throws UnsupportedOperationException
	 */
	@Override
	public T next() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Removes the current entry/player from the ring
	 * 
	 * @return true if the elimination went successfully, otherwise false
	 * @throws UnsupportedOperationException
	 */
	@Override
	public boolean eliminate() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Checks if the ring iterator has more players.
	 * 
	 * @return true if the iteration has more entries, otherwise returns false.
	 * @throws UnsupportedOperationException
	 */
	@Override
	public boolean hasNext() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
