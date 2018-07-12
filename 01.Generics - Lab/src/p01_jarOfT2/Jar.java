package p01_jarOfT2;

import java.util.ArrayList;
import java.util.List;

public class Jar<T> {
    private List<T> collection;

    public Jar() {
        this.collection = new ArrayList<>();
    }

    public void add(T element) {
        this.collection.add(element);
    }

    public T remove() {
        if (this.collection.isEmpty()) {
            return null;
        }

        return this.collection.remove(this.collection.size() - 1);
    }
}
