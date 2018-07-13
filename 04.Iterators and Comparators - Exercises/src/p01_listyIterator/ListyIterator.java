package p01_listyIterator;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListyIterator <T> {
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
}
