/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bags;

import java.util.Arrays;

/**
 *
 * @author Mark
 */
public class ResizingArrayBag<T> implements BagInterface<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] contents;
    private int numInBag;

    private static final int MAX_CAPACITY = 10000;
    private boolean initialized = false;

    public ResizingArrayBag(int capacity) {
        if (0 < capacity && capacity <= MAX_CAPACITY) {
            @SuppressWarnings("unchecked")
            T[] temp = (T[]) new Object[capacity];
            contents = temp;
            numInBag = 0;
            initialized = true;
        } else {
            throw new IllegalArgumentException("Invalid capacity: " + capacity);
        }
    }

    public ResizingArrayBag() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public int getCurrentSize() {
        return numInBag;
    }

    @Override
    public boolean isEmpty() {
        return numInBag == 0;
    }

    @Override
    public boolean add(T newEntry) {
        checkInitialization();
        if (numInBag == contents.length) {
            if (!resize()) {
                // couldn't actually resize the array
                return false;
            }
        }
        contents[numInBag] = newEntry;
        ++numInBag;
        return true;
    }

    @Override
    public boolean remove(T anItem) {
        checkInitialization();
        int posn = findItem(anItem);
        if (posn < 0) {
            return false;
        } else {
            remove(posn);
            return true;
        }
    }

    @Override
    public T remove() {
        checkInitialization();
        if (numInBag == 0) {
            return null;
        } else {
            T result = remove(numInBag - 1);
            return result;
        }

    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    }

    @Override
    public boolean contains(T anEntry) {
        return findItem(anEntry) >= 0;
    }

    @Override
    public int getFrequency(T anItem) {
        checkInitialization();
        int count = 0;
        for (int i = 0; i < numInBag; ++i) {
            if (contents[i].equals(anItem)) {
                ++count;
            }
        }
        return count;

    }

    @Override
    public T[] toArray() {
        return Arrays.copyOf(contents, numInBag);
    }

    private void checkInitialization() {
        if (!initialized) {
            throw new SecurityException("Uninitialized ArrayBag");
        }
    }

    private int findItem(T anItem) {
        for (int posn = 0; posn < numInBag; ++posn) {
            if (contents[posn].equals(anItem)) {
                return posn;
            }
        }
        return -1;
    }

    private T remove(int posn) {
        T result = contents[posn];
        contents[posn] = contents[numInBag - 1];
        contents[numInBag - 1] = null;
        --numInBag;
        return result;
    }

    private boolean resize() {
        int oldSize = contents.length;
        int newSize = Math.min(MAX_CAPACITY, 2 * oldSize);
        if (newSize > oldSize) {
            contents = Arrays.copyOf(contents, newSize);
            return true;
        } else {
            return false;
        }
    }
}
