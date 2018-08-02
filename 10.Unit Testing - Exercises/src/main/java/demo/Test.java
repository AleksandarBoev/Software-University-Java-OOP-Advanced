package demo;

import java.util.ArrayList;
import java.util.List;

public class Test<T> {
    private List<T> collection;

    public Test() {
        this.collection = new ArrayList<>();
    }

    public List<T> getCollection() {
        return this.collection;
    }
}
