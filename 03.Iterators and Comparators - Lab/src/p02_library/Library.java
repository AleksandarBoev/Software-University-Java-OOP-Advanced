package p02_library;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Library<T> implements Iterable<T> {
    private List<T> collection;

    public Library(T... varargs) {
        this.collection = new ArrayList<>(Arrays.asList(varargs));
    }

    @Override
    public Iterator<T> iterator() {
        return new LibIterator();
    }

    private final class LibIterator implements Iterator<T> {
        private int counter = 0;


        @Override
        public boolean hasNext() {
            return counter != collection.size();
        }

        @Override
        public T next() {
            return collection.get(counter++);
        }
    }
}
