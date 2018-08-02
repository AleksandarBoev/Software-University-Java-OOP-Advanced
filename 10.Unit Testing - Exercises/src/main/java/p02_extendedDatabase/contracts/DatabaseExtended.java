package p02_extendedDatabase.contracts;

import javax.naming.OperationNotSupportedException;

public interface DatabaseExtended<T> {
    void add(T element) throws OperationNotSupportedException;

    void remove() throws OperationNotSupportedException;

    T[] fetch();//TODO "T" is referent type of data. Which means someone can change the value of the collection via the fetched array. Ask how to fix this.

}
