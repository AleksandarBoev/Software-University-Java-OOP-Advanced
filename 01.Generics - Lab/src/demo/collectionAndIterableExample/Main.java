package demo.collectionAndIterableExample;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Collection<String> stringCollection = new ArrayList<>();

        stringCollection.add("hello");
        stringCollection.add("hello1");
        stringCollection.add("hello2");
        stringCollection.add("hello3");
        stringCollection.add("hello4");

        List<String> existingArrayListOfStrings = new ArrayList<>();
        String[] existingArrayOfStrings = new String[]{"hello", "no"};
        Set<String> existingSetOfStrings = new LinkedHashSet<>();
        Iterable<String> anIterableCollection = existingArrayListOfStrings;
        anIterableCollection = existingSetOfStrings;

        for (String s : anIterableCollection) {
            System.out.println(s);
        }

    }
}
