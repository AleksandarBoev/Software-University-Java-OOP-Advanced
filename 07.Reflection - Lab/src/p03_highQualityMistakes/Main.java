package p03_highQualityMistakes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Field[] fields = Arrays.stream(Reflection.class.getDeclaredFields())
                .sorted((f1, f2) -> {
                    return f1.getName().compareTo(f2.getName());
                })
                .toArray(n -> new Field[n]);


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

        for (Field field : fields) {
            if (!Modifier.isPrivate(field.getModifiers()))
                System.out.printf("%s must be private!%n", field.getName());

        }

        for (Method getter : getters) {
            if (!Modifier.isPublic(getter.getModifiers()))
                System.out.printf("%s have to be public!%n", getter.getName());
        }

        for (Method setter : setters) {
            if (!Modifier.isPrivate(setter.getModifiers()))
                System.out.printf("%s have to be private!%n", setter.getName());
        }
    }
}
