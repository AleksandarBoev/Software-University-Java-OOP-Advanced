package demo;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }

    private static void doStuff(String... values) { //like String[], but more agile
        for (String value : values) {

        }

        String[] tokens = Arrays.stream(values).toArray(n -> new String[n]); //TODO vij dali nqma po-dobur nachin

        for (int i = 0; i < values.length; i++) {

        }
    }

    private static void doMoreStuff(String word, Integer... values) {

    }
}
