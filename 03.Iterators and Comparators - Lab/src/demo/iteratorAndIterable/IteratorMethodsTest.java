package demo.iteratorAndIterable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IteratorMethodsTest {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>(Arrays.asList(new String[]{"hello", "hi", "this is a word", "hello", "good day!", "printRest", "aaa", "wh", "t"}));
        Iterator<String> iterator = stringList.iterator(); // these are the methods of an iterator:

        while (iterator.hasNext()) {
            String currentWord = iterator.next();
            if ("hello".equals(currentWord)) {
                iterator.remove();
                System.out.printf("Word \"%s\" has been removed!%n", currentWord);
            } else if ("printRest".equals(currentWord)) {
                iterator.forEachRemaining(t -> System.out.println(t)); // Consumer function. After this method, the cycle ends.
            } else {
                System.out.println(currentWord);
            }
        }
    }
}
