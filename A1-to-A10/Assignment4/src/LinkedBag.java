/**
 * A class of bags whose entries are stored in a chain of linked nodes. The bag
 * is never full.
 *
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @author Mark Young (A00000000)
 * @version 4.2
 */
public final class LinkedBag<T> implements BagInterface<T> {

	private Node firstNode; // Reference to first node
	private int numberOfEntries; // how many items are in the bag
									// (so we don't have to count every time)

	/**
	 * Create an empty Bag.
	 */
	public LinkedBag() {
		firstNode = null;
		numberOfEntries = 0;
	} // end default constructor

	/**
	 * Return whether this bag is empty.
	 *
	 * @return True if this bag is empty, or false if not.
	 */
	public boolean isEmpty() {
		return numberOfEntries == 0;
	} // end isEmpty

	/**
	 * Gets the number of entries currently in this bag.
	 *
	 * @return The integer number of entries currently in this bag.
	 */
	public int getCurrentSize() {
		return numberOfEntries;
	} // end getCurrentSize

	/**
	 * Adds a new entry to this bag.
	 *
	 * @param newEntry
	 *            The object to be added as a new entry
	 * @return True if the addition is successful, or false if not.
	 */
	public boolean add(T newEntry) { // OutOfMemoryError possible
		// Add to beginning of chain:
		Node newNode = new Node(newEntry);
		newNode.next = firstNode; // Make new node reference rest of chain
		// (firstNode is null if chain is empty)
		firstNode = newNode; // New node is at beginning of chain
		numberOfEntries++;

		return true;
	} // end add

	/**
	 * Retrieves all entries that are in this bag.
	 *
	 * @return A newly allocated array of all the entries in this bag.
	 */
	public T[] toArray() {
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries]; // Unchecked cast

		Node currentNode = firstNode;
		for (int index = 0; index < numberOfEntries; ++index) {
			result[index] = currentNode.data;
			currentNode = currentNode.next;
		} // end for

		return result;
	} // end toArray

	/**
	 * Counts the number of times a given entry appears in this bag.
	 *
	 * @param anEntry
	 *            The entry to be counted.
	 * @return The number of times anEntry appears in this bag.
	 */
	public int getFrequencyOf(T anEntry) {
		int frequency = 0;

		Node currentNode = firstNode;
		for (int counter = 0; counter < numberOfEntries; ++counter) {
			if (anEntry.equals(currentNode.data)) {
				frequency++;
			} // end if

			currentNode = currentNode.next;
		} // end for

		return frequency;
	} // end getFrequencyOf

	/**
	 * Tests whether this bag contains a given entry.
	 *
	 * @param anEntry
	 *            The entry to locate.
	 * @return True if the bag contains anEntry, or false otherwise.
	 */
	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;

		for (int i = 0; !found && i < numberOfEntries; ++i) {
			if (anEntry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.next;
		} // end for

		return found;
	} // end contains

	// Locates a given entry within this bag.
	// Returns a reference to the node containing the entry, if located,
	// or null otherwise.
	private Node findNode(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;

		for (int i = 0; !found && i < numberOfEntries; ++i) {
			if (anEntry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.next;
		} // end for

		return currentNode;
	} // end findNode

	/**
	 * Removes all entries from this bag.
	 */
	public void clear() {
		while (!isEmpty())
			remove();
	} // end clear

	/**
	 * Removes one unspecified entry from this bag, if possible.
	 *
	 * @return Either the removed entry, if the removal was successful, or null.
	 */
	public T remove() {
		T result = null;
		if (firstNode != null) {
			result = firstNode.data;
			firstNode = firstNode.next; // Remove first node from chain
			numberOfEntries--;
		} // end if

		return result;
	} // end remove

	/**
	 * Removes one occurrence of a given entry from this bag, if possible.
	 *
	 * @param anEntry
	 *            The entry to be removed.
	 * @return True if the removal was successful, or false otherwise.
	 */
	public boolean remove(T anEntry) {
		boolean result = false;
		Node nodeN = findNode(anEntry);

		if (nodeN != null) {
			// Replace located entry with entry in first node
			nodeN.data = firstNode.data;

			// Remove first node
			firstNode = firstNode.next;
			numberOfEntries--;

			result = true;
		} // end if

		return result;
	} // end remove

	// Private class for holding the data and pointers
	private class Node {
		public T data; // Entry in bag
		public Node next; // Link to next node

		public Node(T dataPortion) {
			this(dataPortion, null);
		} // end constructor

		public Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		} // end constructor
	} // end Node

	/**
	 * Returns a string representation of the bag elements
	 * e.g. if the bag contains A, B and C, the String returned
	 *  could be (A, B, C). However order isn't important.
	 *  returns empty round brackets () for empty bag
	 * 
	 * @return a string containing bag entries
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		//proceed only if there're some entries in the bag
		if (!this.isEmpty()) {
			Node currentNode = firstNode;
			for (int i = 0; i < getCurrentSize(); ++i) {
				sb.append(currentNode.data);
				currentNode = currentNode.next;
				//skip appending comma and space if its last element 
				if (i != getCurrentSize() - 1) {
					sb.append(", ");
				} // end if
			} // end for
		} // end if
		return sb.append(")").toString();
	} // end toString

	/**
	 * Removes every occurrences of an Entry from the bag
	 * 
	 * e.g. expunging A from a bag containing three As, 
	 * two Bs and a C would result in the bag containing 
	 * only two Bs and a C.
	 * 
	 * @param anEntry 
	 * 			The entry to be expunged
	 */
	public void expunge(T anEntry) {
		int frequency = getFrequencyOf(anEntry);
		//do nothing if the entry doesn't exist in the bag
		if (frequency > 0) {
			int entriesToRemove = frequency;
			Node currentNode = firstNode;
			for (int i = 0; i < getCurrentSize(); ++i) {
				//if entry exist then remove it, otherwise go to next entry
				if (anEntry.equals(currentNode.data)) {
					// Replace located entry with entry in first node
					currentNode.data = firstNode.data;
					// Remove first node
					firstNode = firstNode.next;
					entriesToRemove--;
					if (entriesToRemove == 0) {
						//all entries removed from bag, no need to continue
						break;
					} // end if
				} // end if
				currentNode = currentNode.next;
			} // end for
			//reduce bag size
			numberOfEntries -= frequency;
		} // end if
	} // end expunge

	/**
	 * Moves every element from the current bag into the other bag.
	 * e.g. Before  :: bag 1 -> (A, B, C) 
	 *      		   bag 2 -> (A, D, F)
	 * After moveTo :: bag 1 -> ()
	 *                 bag 2 -> (C, B, A, A, D, F)
	 * @param newBag
	 *        The target bag where all the entries to be copied
	 */
	public void moveTo(LinkedBag<T> newBag) {
		//nothing to do if this bag is empty
		if (!this.isEmpty()) {
			for (int i = 0; i < getCurrentSize(); ++i) {
				//add entry to target bag
				newBag.add(firstNode.data);
				//remove the first node
				firstNode = firstNode.next;
			} // end for
			//reduce bag size
			numberOfEntries -= getCurrentSize();
		} // end if
	} // end moveTo

} // end LinkedBag
