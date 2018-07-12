package demo.queueAndStackUsingGenerics;

import java.util.ArrayList;
import java.util.List;

public abstract class CollectionImpl<T> implements Collection2<T> {
    protected List<T> collection;

    public CollectionImpl() {
        this.collection = new ArrayList();
    }


    @Override
    public void add(T element) {
        this.collection.add(element);
    }
}
