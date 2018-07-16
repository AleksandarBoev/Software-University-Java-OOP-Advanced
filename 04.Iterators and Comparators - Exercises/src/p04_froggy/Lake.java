package p04_froggy;

import java.util.Iterator;

public class Lake implements Iterable {
    private Integer[] numbersToBeIterated;

    public Lake(Integer... integers) {
        this.numbersToBeIterated = integers;
    }

    @Override
    public Iterator iterator() {
        return new Frog();
    }

    private final class Frog implements Iterator {
        private int cursor;
        private boolean iteratingEvenNums;

        public Frog() {
            this.cursor = -2;
            this.iteratingEvenNums = true;
        }

        @Override
        public boolean hasNext() {
            boolean hasNext = this.cursor + 2 < numbersToBeIterated.length;
            if (hasNext) {
                return hasNext;
            } else if (this.iteratingEvenNums && numbersToBeIterated.length > 1) { //it was iterating even numbers untill now and there is atleast one odd number
                this.cursor = -1;
                this.iteratingEvenNums = false;
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Integer next() {
            return numbersToBeIterated[cursor+=2];
        }
    }
}
