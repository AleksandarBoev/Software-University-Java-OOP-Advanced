package p02_genericArrayCreator;

import java.lang.reflect.Array;

public class ArrayCreator<T> {
    public static <T> T[] create(int length, T item) {
        T[] result = (T[])new Object[length];

        for (int i = 0; i < result.length; i++) {
            result[i] = item;
        }

        return result;
    }

    public static <T> T[] create(Class<T> cl, int length, T item) {
        T[] result = (T[]) Array.newInstance(cl, length); //TODO have figure out what this does
        for (int i = 0; i < result.length; i++) {
            result[i] = item;
        }
        return result;
    }

}
