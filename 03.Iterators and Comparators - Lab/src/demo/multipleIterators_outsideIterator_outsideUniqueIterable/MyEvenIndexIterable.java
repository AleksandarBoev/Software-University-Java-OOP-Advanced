package demo.multipleIterators_outsideIterator_outsideUniqueIterable;

import java.util.Iterator;
import java.util.List;

public class MyEvenIndexIterable<T, C extends List<T>> implements Iterable<T> {
    private C collection;

    public MyEvenIndexIterable(C collection) {
        this.collection = collection;
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomIterator();
    }

    private final class CustomIterator implements Iterator<T> {
        private int cursor;

        public CustomIterator() {
            this.cursor = -2;
        }

        @Override
        public boolean hasNext() {
            return cursor + 2 < collection.size();
        }

        @Override
        public T next() {
            return collection.get(cursor+=2);
        }
    }
}
