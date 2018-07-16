package p06_strategyPattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        //3
        //Pesho 20
        //Joro 100
        //Pencho 1
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfInputs = Integer.parseInt(reader.readLine());
        Set<Person> comparedByName = new TreeSet<>(new PersonComparatorByNameLengthAndFirstLetter());
        Set<Person> comparedByAge = new TreeSet<>(new PersonComparatorByAge());

        for (int i = 0; i < numberOfInputs; i++) {
            String[] personTokens = reader.readLine().split(" ");
            Person currentPerson = new Person(personTokens[0], Integer.parseInt(personTokens[1]));

            comparedByName.add(currentPerson);
            comparedByAge.add(currentPerson);
        }

        for (Person person : comparedByName) {
            System.out.println(person);
        }

        for (Person person : comparedByAge) {
            System.out.println(person);
        }
    }
}
