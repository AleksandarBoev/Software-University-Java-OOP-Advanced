package p01_jarOfT;

public interface Jar<T> {
    void add(T element);
    T remove();
}
