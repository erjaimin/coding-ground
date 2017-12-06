package hashMap;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * represents the hash table data structure and related operations
 * @author 
 *
 * @param <K> key
 * @param <V> value
 */
class MyHashTable<K,V> {
	
	/**
	 * Number of entries in the HashTable.
	 */
	private int entryCount = 0;

	/**
	 * Number of buckets. The constructor sets this variable to its initial value,
	 * which eventually can get changed by invoking the rehash() method.
	 */
	private int numBuckets;

	/**
	 * Threshold load factor for rehashing.
	 */
	private final double MAX_LOAD_FACTOR=0.75;

	/**
	 *  Buckets to store lists of key-value pairs.
	 *  Traditionally an array is used for the buckets and
	 *  a linked list is used for the entries within each bucket.   
	 *  We use an Arraylist rather than an array, since the former is simpler to use in Java.   
	 */

	ArrayList<HashLinkedList<K,V> >  buckets;

	/**
	 * creates an insatnce of {@link MyHashTable}
	 * @param numBuckets is the initial number of buckets used by this hash table
	 */
	MyHashTable(int numBuckets) {
		this.numBuckets = numBuckets;
		initializeBuckets(numBuckets);
	}

	/**
	 * Given a key, return the bucket position for the key. 
	 */
	private int hashFunction(K key) {

		return  Math.abs(key.hashCode()) % numBuckets ;
	}

	/**
	 * Checking if the hash table is empty.  
	 */
	public boolean isEmpty()
	{
		return entryCount == 0;
	}

	/**
	 *   return the number of entries in the hash table.
	 */
	public int size()
	{
		return entryCount;
	}

	/**
	 * Adds a key-value pair to the hash table. If the load factor goes above the 
	 * MAX_LOAD_FACTOR, then call the rehash() method after inserting. 
	 * 
	 *  If there was a previous value for the given key in this hashtable,
	 *  then overwrite it with new value and return the old value.
	 *  Otherwise return null.   
	 */
	public  V  put(K key, V value) {
		HashLinkedList<K, V> hashLinkedList = getHashLinkedListByKey(key);
		if(hashLinkedList.isEmpty()){
			entryCount++;
		}
		// get the old value if key exists
		V oldValue = get(key);
		hashLinkedList.add(key, value);
		// increase the capacity if reached load factor
		if(checkLoadFactor()){
			rehash();
		}
		if(oldValue != null){
			return oldValue;
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	private boolean checkLoadFactor() {
		double currentLoadFactor = (double)entryCount/numBuckets;
		return currentLoadFactor > MAX_LOAD_FACTOR;
		
	}

	/**
	 * Retrieves a value associated with some given key in the hash table.
     Returns null if the key could not be found in the hash table)
	 */
	public V get(K key) {
		HashLinkedList<K, V> hashLinkedList = getHashLinkedListByKey(key);
		if(!hashLinkedList.isEmpty()){
			HashNode<K, V> listNode = hashLinkedList.getListNode(key);
			if(listNode != null)
				return listNode.getValue();
		}
		return null;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	private HashLinkedList<K, V> getHashLinkedListByKey(K key) {
		int index = hashFunction(key);
		return this.buckets.get(index);
	}

	/**
	 * Removes a key-value pair from the hash table.
	 * Return value associated with the provided key.   If the key is not found, return null.
	 */
	public V remove(K key) {
		HashLinkedList<K, V> hashLinkedList = getHashLinkedListByKey(key);
		if(!hashLinkedList.isEmpty()){
			HashNode<K, V> listNode = hashLinkedList.remove(key);
			if(listNode != null)
				return listNode.getValue();
		}
		return null;
	}

	/**
	 * This method is used for testing rehash().  Normally one would not provide such a method. 
	 * @return
	 */
	public int getNumBuckets(){
		return numBuckets;
	}

	/**
	 * Returns an iterator for the hash table. 
	 * @return
	 */
	public MyHashTable<K, V>.HashIterator  iterator(){
		return new HashIterator();
	}

	/**
	 * Removes all the entries from the hash table, 
	 * but keeps the number of buckets intact.
	 */
	public void clear()
	{
		for (int ct = 0; ct < buckets.size(); ct++){
			buckets.get(ct).clear();
		}
		entryCount=0;		
	}

	/**
	 * Create a new hash table that has twice the number of buckets.
	 */
	public void rehash()
	{
		MyHashTable<K, V>.HashIterator iterator = iterator();
		this.numBuckets = this.numBuckets * 2;
		initializeBuckets(this.numBuckets);
		while(iterator.hasNext()){
			try {
				HashNode<K, V> listNode = (HashNode<K, V>) iterator.next().clone();
				put(listNode.getKey(), listNode.getValue());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Checks if the hash table contains the given key.
	 * Return true if the hash table has the specified key, and false otherwise.
	 * @param key
	 * @return true if the hash table contains the given key, otherwise returns false
	 */
	public boolean containsKey(K key)
	{
		int hashValue = hashFunction(key);
		if(buckets.get(hashValue).getListNode(key) == null){
			return false;
		}
		return true;
	}

	/**
	 * return an ArrayList of the keys in the hashtable
	 * @return
	 */
	public ArrayList<K>  keys()
	{
		ArrayList<K>  listKeys = new ArrayList<>();
		MyHashTable<K, V>.HashIterator iterator = this.iterator();
		while(iterator.hasNext()){
			HashNode<K, V> hashNode = iterator.next();
			listKeys.add(hashNode.getKey());
		}
		return listKeys;
	}

	/**
	 * return an ArrayList of the values in the hashtable
	 * @return
	 */
	public ArrayList<V> values()
	{
		ArrayList<V>  listValues = new ArrayList<>();
		MyHashTable<K, V>.HashIterator iterator = this.iterator();
		while(iterator.hasNext()){
			HashNode<K, V> hashNode = iterator.next();
			listValues.add(hashNode.getValue());
		}
		return listValues;
	}

	@Override
	public String toString() {
		/**
		 * Implemented method. You do not need to modify.
		 */
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buckets.size(); i++) {
			sb.append("Bucket ");
			sb.append(i);
			sb.append(" has ");
			sb.append(buckets.get(i).size());
			sb.append(" entries.\n");
		}
		sb.append("There are ");
		sb.append(entryCount);
		sb.append(" entries in the hash table altogether.");
		return sb.toString();
	}
	
	/**
	 * resets the buckets and initialize each with empty {@link HashLinkedList}
	 * @param numBuckets current no. of buckets
	 */
	private void initializeBuckets(int numBuckets) {
		this.buckets = new ArrayList<>(numBuckets);
		this.entryCount=0;
		for(int i=0; i<numBuckets; i++){
			this.buckets.add(new HashLinkedList<>());
		}
	}

	/**
	 *    Inner class:   Iterator for the Hash Table.
	 */
	public class HashIterator implements  Iterator<HashNode<K,V> > {
		HashLinkedList<K,V>  allEntries;

		/**
		 * Creates an insatnce of HashIterator
		 */
		public  HashIterator()
		{
			this.allEntries = new HashLinkedList<>();
			for(int i=0; i<numBuckets; i++){
				HashLinkedList<K, V> hashLinkedList = buckets.get(i);
				HashNode<K, V> first = hashLinkedList.getFirst();
				while(first != null){
					allEntries.add(first.getKey(), first.getValue());
					first = first.getNext();
				}
			}
		}

		@Override
		public boolean hasNext()
		{
			return !allEntries.isEmpty();
		}

		@Override
		public HashNode<K,V> next()
		{
			return allEntries.removeFirst();
		}

		@Override
		public void remove() {
			allEntries.removeFirst();
		}		
	}
}
