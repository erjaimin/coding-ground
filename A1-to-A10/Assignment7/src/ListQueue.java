import java.util.LinkedList;
import java.util.List;

/**
 * A simple implementation of the QueueInterface. Uses a List to hold the Queue
 * elements.  Throws the exceptions the List object throws without intercepting
 * them.
 *
 * @author Mark Young (A00000000)
 * @param <T> the base type of this queue
 */
public class ListQueue<T> implements QueueInterface<T> {

    private final List<T> LIST;

    public ListQueue() {
        LIST = new LinkedList<>();
    }

    @Override
    public boolean add(T item) {
        LIST.add(item);
        return true;
    }

    @Override
    public T remove() {
        return LIST.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return LIST.isEmpty();
    }

    @Override
    public T front() {
        return LIST.get(0);
    }

    @Override
    public String toString() {
        return LIST.toString();
    }

}
