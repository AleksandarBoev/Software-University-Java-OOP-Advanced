package p05_comparingObjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) throws IOException {
        //Pesho 22 Vraca
        //Gogo 22 Vraca
        //Gogo 22 Vraca
        //END
        //2

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        People people = new People();

        String input;
        while(!"END".equals(input = reader.readLine())) {
            String[] personTokens = input.split(" ");
            Person currentPerson = new Person(personTokens[0], Integer.parseInt(personTokens[1]), personTokens[2]);
            people.addPerson(currentPerson);
        }
        int personOfInterestIndex = Integer.parseInt(reader.readLine());
        reader.close();

        Person personOfInterest = null;
        try {
            personOfInterest = people.getPerson(personOfInterestIndex);
            System.out.printf("%d %d %d",
                    people.getNumberOfEqualPeople(personOfInterest), people.getNumberOfDifferendPeople(personOfInterest), people.getNumberOfPeople());
        } catch (NoSuchElementException nsee) {
            System.out.println(nsee.getMessage());
        }

    }
}
