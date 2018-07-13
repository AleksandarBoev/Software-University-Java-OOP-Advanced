package demo.iteratorAndIterable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IterableTest {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>(Arrays.asList(new String[]{"hi", "hello", "good day"}));

        Iterable<String> iterable = stringList;
        for (String s : iterable) {
            System.out.println(s);
        }

        String[] stringArray = new String[]{"hi", "hello", "good day"};

//        iterable = stringArray; //CTE
    }
}
