package p06_strategyPattern;

import java.util.Comparator;

public class PersonComparatorByNameLengthAndFirstLetter implements Comparator<Person> {// need to think of a more creative name...
    @Override
    public int compare(Person person1, Person person2) {
        int comparisonResult = Integer.compare(person1.getNameLength(), person2.getNameLength());
        if (comparisonResult != 0)
            return comparisonResult;
        else
            return person1.getFirstLetter().toLowerCase().compareTo(person2.getFirstLetter().toLowerCase());
    }
}
