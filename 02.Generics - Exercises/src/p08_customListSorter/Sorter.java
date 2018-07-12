package p08_customListSorter;

import java.util.stream.Collectors;

public class Sorter <T extends Comparable<T>>{
    public static <T extends Comparable<T>> void sort(CustomList<T> customList) {
        customList.setCollection(customList.getCollection().stream()
                .sorted((t1, t2) -> {
                    return t1.compareTo(t2);
                }).collect(Collectors.toList()));
    }
}
