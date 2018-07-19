package demo.multipleIterators_outsideIterator_outsideUniqueIterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyClass { // making two different Iterators in the same class.
    private List<Integer> numbers;

    public MyClass(int numbersLength) {
        this.numbers = new ArrayList<>();
        for (int i = 1; i <= numbersLength; i++) {
            this.numbers.add(i);
        }
    }

    public void printOddIndexes() {
        OddIterator iterator = new OddIterator();
        //why OddIterator on the left and not Iterator? Because when trying to write currentNumber = iterator.next, the compiler will not let me unless I make
        //a strange type cast. So it's better this way.
        while (iterator.hasNext()) {
            int currentNumber = iterator.next();
            System.out.println(currentNumber);
        }
    }

    public void printEvenIndexes() {
        EvenIterator iterator = new EvenIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private final class OddIterator implements Iterator<Integer> {
        private int cursor;

        public OddIterator() {
            this.cursor = -1;
        }

        @Override
        public boolean hasNext() {
            return cursor + 2 < numbers.size();
        }

        @Override
        public Integer next() {
            return numbers.get(cursor+=2);
        }
    }

    private final class EvenIterator implements Iterator<Integer> {

        private int cursor;

        public EvenIterator() {
            this.cursor = -2;
        }

        @Override
        public boolean hasNext() {
            return cursor + 2 < numbers.size();
        }

        @Override
        public Integer next() {
            return numbers.get(cursor+=2);
        }
    }
}
