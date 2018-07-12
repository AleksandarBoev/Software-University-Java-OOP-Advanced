package demo.universalSortAndComparableExample;

public class Sort <T extends Comparable<T>> {
    public Sort() {
    }

    public T[] sortArrayAscending(T[] array) {
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

    public T[] sortArrayDescending(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            T max = array[i];
            int swapIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (max.compareTo(array[j]) < 0) {
                    swapIndex = j;
                    max = array[j];
                }
            }
            array[swapIndex] = array[i];
            array[i] = max;
        }

        return array;
    }
}
