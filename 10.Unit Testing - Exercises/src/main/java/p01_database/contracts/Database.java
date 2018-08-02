package p01_database.contracts;

import javax.naming.OperationNotSupportedException;

public interface Database<T > {
    void add(Integer element) throws OperationNotSupportedException;

    void remove() throws OperationNotSupportedException;

    Integer[] fetch();
}
