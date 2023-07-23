package Model;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<T> {
    private T[] arr;
    private final int ENLARGE_FACTOR = 2;
    private final int STARTING_SIZE = 1;
    private int currentSize;

    public MyArrayList() {
        arr = (T[]) new Object[STARTING_SIZE];
        currentSize = 0;
    }

    private void enlargeArray() {
        arr = Arrays.copyOf(arr, ENLARGE_FACTOR * currentSize);
    }

    public void add(T newValue) {
        if (currentSize == arr.length) {
            enlargeArray();
        }
        arr[currentSize++] = newValue;
    }

    public Memento saveStateToMemento() {
        return new Memento(arr, currentSize);
    }

    public void restoreStateFromMemento(Memento memento) {
    	currentSize = memento.getCurrentSize();
        this.arr = memento.getArr().clone();
        
    }
    public MyArrayList<T> clone() {
        MyArrayList<T> clone = new MyArrayList<>();
        clone.arr = this.arr.clone();
        clone.currentSize = this.currentSize;
        return clone;
    }

    private class MyArrayIterator<T> implements Iterator<T> {
        private int index;

        public MyArrayIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < arr.length;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            return (T) arr[index++];
        }

        @Override
        public void remove() {
            if (index > 0) {
                arr[--index] = null;
            }
        }

        public String toString() {
            return arr.toString();
        }
    }

    public Iterator<T> iterator() {
        return new MyArrayIterator<T>();
    }

    public  class Memento {
        private final T[] arr;
        private final int currentSize;

        public Memento(T[] arr, int currentSize) {
            this.arr =arr.clone();
            this.currentSize = currentSize;
        }

        public T[] getArr() {
            return arr;
        }

        public int getCurrentSize() {
            return currentSize;
        }
    }
}