package demo.universalSortAndComparableExample;

import java.util.ArrayList;
import java.util.List;

public class MyCollection<T> {
    private List<T> collection;

    public MyCollection() {
        this.collection = new ArrayList<>();
    }

    public void add(T element) {
        this.collection.add(element);
    }

    public T remove() {
        return this.collection.remove(this.collection.size() - 1);
    }

    public T get(int index) {
        return this.collection.get(index);
    }
}
