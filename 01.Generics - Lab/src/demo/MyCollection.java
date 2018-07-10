package demo;

import java.util.ArrayList;
import java.util.List;

public abstract class MyCollection<T> {
    protected List<T> collection;

    public MyCollection(List<T> collection) {
        this.collection = new ArrayList<>();
    }

    public abstract T addAllElements();
}
