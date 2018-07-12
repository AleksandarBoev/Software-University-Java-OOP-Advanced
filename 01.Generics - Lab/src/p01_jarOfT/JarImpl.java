package p01_jarOfT;

import java.util.ArrayList;
import java.util.List;

public class JarImpl<T> implements Jar<T> {
    private List<T> collection;

    public JarImpl() {
        this.collection = new ArrayList<>();
    }

    @Override
    public void add(T element) {
        this.collection.add(element);
    }

    @Override
    public T remove() {
        return this.collection.remove(this.collection.size() - 1);
    }
}
