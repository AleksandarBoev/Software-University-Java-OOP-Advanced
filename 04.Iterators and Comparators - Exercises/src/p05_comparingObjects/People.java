package p05_comparingObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class People {
    private static final String NO_SUCH_PERSON_MESSAGE = "No matches";

    private List<Person> people;

    public People() {
        this.people = new ArrayList<>();
    }

    public void addPerson(Person person) {
        this.people.add(person);
    }

    public Person getPerson(int index) {
        if (index < 0 || index >= this.people.size()) {
            throw new NoSuchElementException(NO_SUCH_PERSON_MESSAGE);
        }

        return this.people.get(index);
    }

    public int getNumberOfEqualPeople(Person person) {
        int count = 0;
        for (Person currentPerson : this.people) {
            if (currentPerson.compareTo(person) == 0) {
                count++;
            }
        }

        return count;
    }

    public int getNumberOfDifferendPeople(Person person) {
        int count = 0;
        for (Person currentPerson : this.people) { //or just return this.people - this.getNumberOfEqialPeople(person)
            if (currentPerson.compareTo(person) != 0) {
                count++;
            }
        }

        return count;
    }

    public int getNumberOfPeople() {
        return this.people.size();
    }
}
