package p02_genericBoxOfInteger;

import java.util.ArrayList;
import java.util.Collection;

public class Box<T> {
    private Collection<T> collection;

    public Box() {
        this.collection = new ArrayList<>();
    }

    public void add(T element) {
        this.collection.add(element);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T item : this.collection) {
            sb.append(item.getClass().getCanonicalName()).append(": ").append(item.toString())
                    .append("\n");
        }

        sb.delete(sb.length() - 1, sb.length());

        return sb.toString();
    }
}
