package p05_codingTracker;

import java.lang.reflect.Method;
import java.util.*;

public class Tracker {
    @Author(name = "Pesho")
    public static void printMethodsByAuthor(Class<?> cl) {
        Map<String, List<String>> authorMethods = new LinkedHashMap<>(); //or just Hashmap like in the word doc
        Method[] methods = cl.getDeclaredMethods();

        for (Method method : methods) {
            String authorName = method.getAnnotation(Author.class).name();
            if (!authorMethods.containsKey(authorName)) {
                authorMethods.put(authorName, new ArrayList<>());
            }

            authorMethods.get(authorName).add(method.getName());
        }

        for (Map.Entry<String, List<String>> stringListEntry : authorMethods.entrySet()) {
            System.out.printf("%s: %s%n", stringListEntry.getKey(), String.join(", ", stringListEntry.getValue()));
        }
    }

    @Author(name = "Gosho")
    public static void main(String[] args) {
        Tracker.printMethodsByAuthor(Tracker.class);
    }
}
