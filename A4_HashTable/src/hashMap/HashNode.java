package hashMap;

import java.util.Hashtable;

/**
 * represents the node in linked list
 * @author
 *
 * @param <K>
 * @param <V>
 */
public  class HashNode<K,V> implements Cloneable{
	private V value;
	private K key;
	public HashNode<K,V> next;

	/**
	 * creates an instance of {@link HashNode}
	 * @param key
	 * @param value
	 */
	HashNode(K key, V value){
		this.setValue(value);
		this.key = key;
		this.next = null;
	}

	/**
	 * makes a copy of {@link HashNode}
	 */
	public Object clone() throws CloneNotSupportedException{ 
		 return (HashNode<K,V>)super.clone();
	}

	 /**
	  * Returns this hash entry's value. 
	  * Assume entry is not null.
	  * @return
	  */
	public V getValue(){
		return this.value;
	}
	
	/**
	 * Returns this hash nodes's key.  
	 * Assume entry is not null.
	 * @return This hash nodes's key
	 */
	public K getKey(){
		return this.key;
	}
	
	/**
	 * Return Next node
	 */
	HashNode<K,V> getNext(){
		return this.next;
	}

	@Override
	public String toString() {
		/**
		 * Implemented method. You do not need to modify.
		 */
		return key.toString()+" : " + getValue().toString();
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(V value) {
		this.value = value;
	}

}
