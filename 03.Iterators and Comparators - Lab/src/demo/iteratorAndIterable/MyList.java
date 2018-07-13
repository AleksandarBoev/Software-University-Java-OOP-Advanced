package demo.iteratorAndIterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyList <T> implements Iterable<T> {
    private List<T> collection;

    public MyList() {
        this.collection = new ArrayList<>();
    }

    public void add(T element) {
        this.collection.add(element);
    }

    @Override
    public Iterator<T> iterator() {
        return new LibIterator();
    }

    private final class LibIterator implements Iterator<T> {
        private int counter = -1;

        @Override
        public boolean hasNext() {
            return counter != collection.size() - 1;
        }

        @Override
        public T next() {
            return collection.get(++counter);
        }
    }
}
