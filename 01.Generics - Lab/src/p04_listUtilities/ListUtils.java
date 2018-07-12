package p04_listUtilities;

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
}
