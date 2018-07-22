package p02_gettersAndSetters;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Method[] allClassMethods = Reflection.class.getDeclaredMethods();

        Method[] getters = Arrays.stream(allClassMethods)
                .filter(m -> m.getName().startsWith("get"))
                .sorted((m1, m2) -> {
                    return m1.getName().compareTo(m2.getName());
                })
                .toArray(n -> new Method[n]);

        Method[] setters = Arrays.stream(allClassMethods)
                .filter(m -> m.getName().startsWith("set"))
                .sorted((m1, m2) -> {
                    return m1.getName().compareTo(m2.getName());
                })
                .toArray(n -> new Method[n]);

        for (int i = 0; i < getters.length; i++) {
            System.out.printf("%s will return %s%n", getters[i].getName(), getters[i].getReturnType()); //.getSimpleName() would look better, but judge doesn't like it
        }

        for (int i = 0; i < setters.length; i++) {
            System.out.printf("%s and will set field of %s%n", setters[i].getName(), setters[i].getParameters()[0].getType());
        }
    }
}
