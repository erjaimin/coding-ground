package hashMap;

/**
 * represents linked list data structure and related operations
 * @author 
 *
 * @param <K> key 
 * @param <V> value
 */
public class HashLinkedList<K,V>{
	
	/**
	 * data members
	 */
	private HashNode<K,V> head;
	private Integer size;

	/**
	 * creates an instance of {@link HashLinkedList}
	 */
	public HashLinkedList(){
		head = null;
		size = 0;
	}

	/**
	 * Add (Hash)node at the front of the linked list
	 * @param key
	 * @param value
	 */
	public void add(K key, V value){
		HashNode<K,V> temp = head;
		while(temp != null){
			if(temp.getKey().equals(key)){
				temp.setValue(value);
				return;
			}
			temp = temp.next;
		}
		size++;
		HashNode<K, V> newHashNode = new HashNode<>(key, value);
		newHashNode.next = head;
		head = newHashNode;
	}

	/**
	 * Get Hash(node) by key
	 * @param key
	 * @return returns the node with key
	 */
	public HashNode<K,V> getListNode(K key){
		HashNode<K,V> temp = head;
		while(temp != null){
			if(temp.getKey().equals(key)){
				return temp;
			}
			temp = temp.next;
		}
		return null;
	}

	/**
	 * Remove the head node of the list
	 * Note: Used by remove method and next method of hash table Iterator
	 * @return first {@link HashNode} if exists otherwise returns null
	 */
	public HashNode<K,V> removeFirst(){
		HashNode<K,V> temp = head;
		if(head != null){
			if(head.next != null){
				head = head.next;
			}else{
				head = null;
			}
			size--;
		}	
		return temp; 
	}

	/**
	 * Remove Node by key from linked list 
	 * @param key
	 * @return {@link HashNode} if exists otherwise return null
	 */
	public HashNode<K,V> remove(K key){
		HashNode<K,V> current = head, prev = null;
        // If head node itself holds the key to be deleted
        if (current != null && current.getKey().equals(key))
        {
            head = current.getNext(); // Changed head
            size--;
            return current;
        }
        // Search for the key to be deleted, keep track of the
        while (current != null && !current.getKey().equals(key))
        {
            prev = current;
            current = current.getNext();
        }    
        if (current == null) 
        	return null;
        prev.next = current.next;
        size--;
        return current;
	}

	/**
	 * Delete the whole linked list
	 */
	public void clear(){
		head = null;
		size = 0;
	}
	
	/**
	 * Check if the list is empty
	 * @return
	 */
	boolean isEmpty(){
		return size == 0? true:false;
	}

	/**
	 * 
	 * @return the size of linked list
	 */
	public int size(){
		return this.size;
	}
	
	/**
	 * returns first {@link HashNode} in linked list
	 * @return
	 */
	public HashNode<K,V> getFirst(){
		return head;
	}
	
}
