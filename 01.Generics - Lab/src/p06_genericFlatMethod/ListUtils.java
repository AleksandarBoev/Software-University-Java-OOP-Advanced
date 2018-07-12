package p06_genericFlatMethod;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public static<T extends Comparable<T>> T getMax(List<T> list) {
        emptyList(list);

        T max = list.get(0);
        for (T item : list) {
            if (max.compareTo(item) < 0) {
                max = item;
            }
        }

        return max;
    }

    public static <T extends Comparable<T>> T getMin(List<T> list) {
        emptyList(list);

        T min = list.get(0);
        for (T item : list) {
            if (min.compareTo(item) > 0) {
                min = item;
            }
        }

        return min;
    }

    private static <T> void emptyList(List<T> listToBeChecked) {
        if (listToBeChecked.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public static <T> List<Integer> getNullIndices(List<T> list) {
        emptyList(list);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                result.add(i);
            }
        }

        return result;
    }

    public static <T> void flatten(List<? super T> destination, List<List<? extends T>> source) {
        for (List<? extends T> listOfExtendedClasses : source) {
            destination.addAll(listOfExtendedClasses);
        }
    }
}
