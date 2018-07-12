package p04_genericSwapMethodInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class Box<T> {
    private Collection<T> collection;

    public Box() {
        this.collection = new ArrayList<>();
    }

    public void add(T element) {
        this.collection.add(element);
    }

    public void swapElements(int[] swapIndices) {
        T[] arrayVersion = (T[])this.collection.toArray(); //to lazy to think how it should be done with a collection
        T temporaryValue = arrayVersion[swapIndices[0]];
        arrayVersion[swapIndices[0]] = arrayVersion[swapIndices[1]];
        arrayVersion[swapIndices[1]] = temporaryValue;
        this.collection = Arrays.stream(arrayVersion).collect(Collectors.toList());
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
