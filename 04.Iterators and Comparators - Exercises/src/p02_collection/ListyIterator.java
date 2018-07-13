package p02_collection;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListyIterator<T> implements Iterable<T> {
    private static final String INVALID_OPERATION_MESSAGE = "Invalid Operation!";

    private List<T> collection;
    private int index;

    public ListyIterator(T... varargs) {
        this.collection = new ArrayList<>(Arrays.asList(varargs));
        index = 0;
    }

    public boolean move() {
        if (this.hasNext()) {
            this.index++;
            return true;
        } else {
            return false;
        }

    }

    public boolean hasNext() {
        return this.collection.size() - 1 != this.index;
    }

    public void print() throws OperationNotSupportedException {
        if (this.collection.isEmpty()) {
            throw new OperationNotSupportedException(INVALID_OPERATION_MESSAGE);
        } else {
            System.out.println(this.collection.get(index));
        }
    }

    public void printAll() {
        StringBuilder sb = new StringBuilder();
        for (T item : this) { //just typing "this" means the method will use MY custom made iterator
            sb.append(item).append(" ");
        }

        sb.delete(sb.length() - 1, sb.length());
        System.out.println(sb.toString());
    }

    public void hello() {

    }

    @Override
    public Iterator<T> iterator() {
        return new ListyIteratorIterator();
    }

    private final class ListyIteratorIterator implements Iterator<T> {
        private int iteratorIndex = -1;

        @Override
        public boolean hasNext() {
            return collection.size() - 1 != this.iteratorIndex;
        }

        @Override
        public T next() {
            return collection.get(++this.iteratorIndex);
        }
    }
}
