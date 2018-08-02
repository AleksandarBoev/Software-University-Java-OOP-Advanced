package p03_iterator;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ListIteratorImpl<T> implements ListIterator<T>{
    private static final String NULL_ELEMENT_PASSED_MESSAGE = "A null element has been passed as parameter!";
    private static final String INVALID_OPERATION_MESSAGE = "Invalid Operation!";

    List<T> collection;
    private int cursor;

    public ListIteratorImpl(Collection<T> collection) throws OperationNotSupportedException {
        if (collection == null)
            throw new OperationNotSupportedException(NULL_ELEMENT_PASSED_MESSAGE);

        this.collection = new ArrayList<>(collection);
        this.cursor = 0;
    }

    @Override
    public boolean move() {
        if (this.hasNext()) {
            this.cursor++;
            return true;
        }

        return false;
    }

    @Override
    public boolean hasNext() {
        return this.cursor + 1 < this.collection.size();
    }

    @Override
    public String getCurrentElementAsString() {
        if (this.collection.isEmpty())
            throw new IllegalStateException(INVALID_OPERATION_MESSAGE);

        return this.collection.get(this.cursor).toString();
    }

    //•	Move - should move an internal index position to the next index in the list, the method should return
    // true if it successfully moved and false if there is no next index.
    //•	HasNext - should return true if there is a next index and false
    // if the index is already at the last element of the list.
    //•	Print - should print the element at the current internal index, calling Print
    // on a collection without elements should throw an appropriate exception with the message "Invalid Operation!".
}
