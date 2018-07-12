package p05_nullFinder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("Hello");
        stringList.add("Kornichiua");
        stringList.add("Guten Tag!");
        stringList.add(null);
        stringList.add("G'day!");
        stringList.add(null);

        Collection<Integer> nullIndices = ListUtils.getNullIndices(stringList);
    }
}
