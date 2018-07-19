package demo.multipleIterators_outsideIterator_outsideUniqueIterable;

import java.util.Iterator;
import java.util.List;

public class MyEvenIndexIterator <T, C extends List<T>> implements Iterator<T> {
    private int cursor;
    private C collection;

    public MyEvenIndexIterator(C collection) {
        this.cursor = -2;
        this.collection = collection;
    }

    @Override
    public boolean hasNext() {
        return cursor + 2 < this.collection.size();
    }

    @Override
    public T next() {
        return this.collection.get(cursor+=2);
    }
}
