package P01_ExtendedArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ExtendedArrayList<T extends Comparable<T>> extends ArrayList<T> {
    //•	min()
    //•	max()

    public ExtendedArrayList() {

    }

    public ExtendedArrayList(T[] array) {
        for (T t : array) {
            super.add(t);
        }
    }

    public T getMin() {
        T min = null;

        Iterator<T> iterator = super.iterator();
        if (iterator.hasNext()) {
            min = iterator.next();
        }

        while (iterator.hasNext()) {
            T currentElement = iterator.next();
            if (min.compareTo(currentElement) > 0) {
                min = currentElement;
            }
        }

        if (min == null) {
            throw new NoSuchElementException();
        }

        return min;
    }

    public T getMax() {
        T max = null;

        Iterator<T> iterator = super.iterator();
        if (iterator.hasNext()) {
            max = iterator.next();
        }

        while (iterator.hasNext()) {
            T currentElement = iterator.next();
            if (max.compareTo(currentElement) < 0) {
                max = currentElement;
            }
        }

        if (max == null) {
            throw new NoSuchElementException();
        }

        return max;
    }
}
