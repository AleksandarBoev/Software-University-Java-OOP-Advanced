package demo;

import p02_extendedDatabase.contracts.Person;
import p02_extendedDatabase.models.PersonImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        RandomClass randomClass1 = new RandomClass(1);
        RandomClass randomClass2 = new RandomClass(2);
        RandomClass randomClass3 = new RandomClass(3);
        RandomClass randomClass4 = new RandomClass(4);

        List<RandomClass> list = new ArrayList<>();
        list.add(randomClass1);
        list.add(randomClass2);
        list.add(randomClass3);
        list.add(randomClass4);

        RandomClass[] copiedArray = (RandomClass[]) list.toArray();

        copiedArray[2].setNumber(99999);

        Person[] people = new PersonImpl[10];
    }
}
