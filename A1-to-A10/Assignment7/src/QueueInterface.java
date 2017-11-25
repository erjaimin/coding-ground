/**
 * The CSCI 2341 Queue Interface.
 * 
 * @author Mark Young (A00000000)
 * @param <T> the base type of the queue
 */
public interface QueueInterface<T> {
    
    /**
     * Add an item to this queue.
     * 
     * @param item the item to add
     * @return true if the item was added; false otherwise
     */
    public boolean add(T item);
    
    /**
     * Return the front element of this Queue without removing it.
     * 
     * @return the front element of this queue
     * 
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public T front();
    
    /**
     * Remove and return the front element of this queue.
     * 
     * @return the front element of this queue
     * 
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public T remove();
    
    /**
     * Return whether this queue is empty.
     * 
     * @return true if the queue is empty; false otherwise
     */
    public boolean isEmpty();
    
}
