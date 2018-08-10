package hell.repository;

import hell.interfaces.OutputWriter;

import java.util.Map;

public interface Repository<T> {
    void put(T element);

    T get(String name);

    void printAll(OutputWriter outputWriter) throws NoSuchFieldException, IllegalAccessException;
}
