package p09_linkedListTraversal;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyLinkedList <T extends Comparable<T>> implements Iterable<T> {
    private List<T> collection;

    public MyLinkedList() {
        this.collection = new LinkedList<>();
    }

    public void add(T element) {
        this.collection.add(element);
    }

    public boolean remove(T element) {
        int index = -1;

        MyLinkedListIterator iterator = new MyLinkedListIterator();
        while (iterator.hasNext()) {
            if (iterator.next().compareTo(element) == 0) {
                index = iterator.getCursorCurrentValue();
                break;
            }
        }

        if (index == -1) {
            return false;
        }

        this.collection.remove(index);
        return true;
    }

    public int getSize() {
        return this.collection.size();
    }


    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    private final class MyLinkedListIterator implements Iterator<T> {
        private int cursor;

        public MyLinkedListIterator() {
            this.cursor = -1;
        }

        @Override
        public boolean hasNext() {
            return this.cursor + 1 < collection.size();
        }

        @Override
        public T next() {
            return collection.get(++this.cursor);
        }

        public int getCursorCurrentValue() {
            return this.cursor;
        }
    }
}
