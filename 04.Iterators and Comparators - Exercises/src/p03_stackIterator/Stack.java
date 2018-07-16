package p03_stackIterator;

public interface Stack<T> extends Iterable<T> {
    void push(T... args);
    void pop();
}
