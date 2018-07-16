package p03_stackIterator;

import java.util.*;

public class StackImpl<T> implements Stack<T> {
    private List<T> collection;

    public StackImpl() {
        this.collection = new ArrayList<>();
    }

    @Override
    public void push(T... varargs) {
        this.collection.addAll(Arrays.asList(varargs));
    }

    @Override
    public void pop() {
        if (this.collection.isEmpty()) {
            throw new NoSuchElementException("No elements"); //not sure if this is the correct exception
        }

        this.collection.remove(this.collection.size() - 1);
    }


    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private final class StackIterator implements Iterator<T> {
        private int cursor;

        public StackIterator() {
            this.cursor = collection.size();
        }

        @Override
        public boolean hasNext() {
            return this.cursor - 1 >= 0;
        }

        @Override
        public T next() {
            return collection.get(--this.cursor);
        }
    }
}
