package demo;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Integer[] integerArray = new Integer[]{5, 9, 1, 20, 4, 28};
        String[] stringArray = new String[] {"z", "g", "a", "b", "t", "c"};

//        System.out.println(getMaxElement(integerArray, 0, integerArray.length));
//        System.out.println(getMaxElement(stringArray, 0, stringArray.length));

        integerArray = sortArray(integerArray);
        System.out.println(getArrayAsString(integerArray));

        System.out.println();

        stringArray = sortArray(stringArray);
        System.out.println(getArrayAsString(stringArray));

    }

    private static <T extends Comparable<T>> T getMaxElement(T[] array, int startIndex, int endIndex) {
        T max = array[0];

        for (T element : array) {
            if (max.compareTo(element) < 0) {
                max = element;
            }
        }

        return max;
    }

    private static <T extends Comparable<T>> T[] sortArray(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            T min = array[i];
            int swapIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (min.compareTo(array[j]) > 0) {
                    swapIndex = j;
                    min = array[j]; //taking the value and the index of the smallest element
                }
            }
            array[swapIndex] = array[i];
            array[i] = min; //swapping the values of the smallest element with the current element from cycle
        }

        return array;
    }

    private static <T> String getArrayAsString(T[] array) {
        StringBuilder sb = new StringBuilder();
        for (T element : array) {
            sb.append(element).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());

        return sb.toString();
    }
}
