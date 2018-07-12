package p08_customListSorter;

import java.util.*;
import java.util.function.Consumer;

public class CustomList<T extends Comparable<T>> implements Iterable<T> {
    private List<T> collection;

    public CustomList() {
        this.collection = new ArrayList<>();
    }

    public List<T> getCollection() {
//        return Collections.unmodifiableList(this.collection);
        return this.collection;
    }

    public void setCollection(List<T> collection) {
        this.collection = collection;
    }

    public void add(T element) {
        this.collection.add(element);
    }

    public T remove(int index) {
        return this.collection.remove(index);
    }

    public boolean contains(T element) {
        return this.collection.contains(element);
    }

    public void swap(int index1, int index2) {
        T value1 = this.collection.get(index1);
        T value2 = this.collection.get(index2);

        this.collection.remove(index1);
        this.collection.add(index1, value2);

        this.collection.remove(index2);
        this.collection.add(index2, value1);
    }

    public int countGreaterThan(T element) {
        int count = 0;
        for (T item : this.collection) {
            if (item.compareTo(element) > 0) {
                count++;
            }
        }

        return count;
    }

    public T getMax() {
        T max = this.collection.get(0);
        for (T item : this.collection) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }

        return max;
    }

    public T getMin() {
        T min = this.collection.get(0);
        for (T item : this.collection) {
            if (item.compareTo(min) < 0) {
                min = item;
            }
        }

        return min;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T item : this.collection) {
            sb.append(item).
                    append("\n");
        }

        sb.delete(sb.length() - 1, sb.length());

        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        Iterator iteratorResult = this.collection.iterator();
        return iteratorResult;
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }
}
