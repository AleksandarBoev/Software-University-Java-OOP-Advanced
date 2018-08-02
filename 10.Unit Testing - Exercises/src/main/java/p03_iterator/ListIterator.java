package p03_iterator;

public interface ListIterator<T> {
    boolean move();

    boolean hasNext();

    String getCurrentElementAsString();
}
