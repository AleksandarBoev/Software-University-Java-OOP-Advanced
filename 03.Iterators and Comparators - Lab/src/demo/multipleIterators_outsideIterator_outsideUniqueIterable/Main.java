package demo.multipleIterators_outsideIterator_outsideUniqueIterable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyClass myClass = new MyClass(10);
        System.out.println("Printing odd indexes (using built in Iterator):");
        myClass.printOddIndexes();
        System.out.println("Printing even indexes (using built in Iterator):");
        myClass.printEvenIndexes();

        System.out.println("Printing even indexes (using unique outside Iterator):");
        List<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        MyEvenIndexIterator<Integer, List<Integer>> iterator = new MyEvenIndexIterator(integerList);
        while (iterator.hasNext()) {
            Integer currentNumber = iterator.next();
            System.out.println(currentNumber);
        }

        System.out.println("Printing even indexes (using unique outside Iterable):");
        MyEvenIndexIterable<Integer, List<Integer>> customIterable = new MyEvenIndexIterable<>(integerList);
        for (Integer integer : customIterable) {
            System.out.println(integer);
        }

        Iterable iterable = new MyEvenIndexIterable<Integer, List<Integer>>(integerList); //lifting the abstract level doesn't work well
    }
}
