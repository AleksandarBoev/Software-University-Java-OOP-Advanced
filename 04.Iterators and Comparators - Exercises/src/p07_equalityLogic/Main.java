package p07_equalityLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        Set<Person> peopleTreeSet = new TreeSet<>();
        Set<Person> peopleHashSet = new HashSet<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfInputs = Integer.parseInt(reader.readLine());

        for (int i = 0; i < numberOfInputs; i++) {
            String[] personTokens = reader.readLine().split(" ");
            Person currentPerson = new Person(personTokens[0], Integer.parseInt(personTokens[1]));

            peopleTreeSet.add(currentPerson); // decides if there are duplicates via comparing them with compareTo
            peopleHashSet.add(currentPerson); // decides if there are duplicates via hashcode and equals methods
        }

        System.out.println(peopleTreeSet.size());
        System.out.println(peopleHashSet.size());
    }
}
