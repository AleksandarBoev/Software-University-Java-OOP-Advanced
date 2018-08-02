package p01_database.models;

import p01_database.contracts.Database;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseImpl implements Database {
    private static final int FIXED_ARRAY_SIZE = 16;
    private static final String ARRAY_LENGTH_IS_TOO_BIG_MESSAGE = String.format("Given collection is bigger than the allowed capacity of %d", FIXED_ARRAY_SIZE);
    private static final String REMOVING_ELEMENT_FROM_EMPTY_COLLECTION_MESSAGE = "Collection is empty, you can't remove elements from it!";
    private static final String PASSING_NULL_ELEMENT_MESSAGE = "You tried passing a null element.";
    private static final String MAXIMUM_CAPACITY_REACHED = "Maximum capacity of collection has been reached.";

    private List<Integer> collection;

    public DatabaseImpl(int... varargs) throws OperationNotSupportedException {
        if (varargs.length > 16) {
            throw new OperationNotSupportedException(ARRAY_LENGTH_IS_TOO_BIG_MESSAGE);
        }
        this.collection = new ArrayList<>(16);

        for (int i = 0; i < varargs.length; i++) {
            this.collection.add(varargs[i]);
        }
    }


    @Override
    public void add(Integer element) throws OperationNotSupportedException {
        if (element == null) {
            throw new OperationNotSupportedException(PASSING_NULL_ELEMENT_MESSAGE);
        }

        if (this.collection.size() == FIXED_ARRAY_SIZE) {
            throw new OperationNotSupportedException(MAXIMUM_CAPACITY_REACHED);
        }

        this.collection.add(element);
    }

    @Override
    public void remove() throws OperationNotSupportedException {
        if (this.collection.isEmpty()) {
            throw new OperationNotSupportedException(REMOVING_ELEMENT_FROM_EMPTY_COLLECTION_MESSAGE);
        }

        this.collection.remove(this.collection.size() - 1);
    }

    @Override
    public Integer[] fetch() {
        return this.collection.stream().toArray(n -> new Integer[n]);
    }
}
