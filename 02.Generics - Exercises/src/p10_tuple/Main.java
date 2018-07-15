package p10_tuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = reader.readLine().split("\\s+");
        String firstVariable = tokens[0] + " " + tokens[1];
        String secondVariable = tokens[2];
        Tuple<String, String> tuple = new Tuple(firstVariable, secondVariable);
        System.out.println(tuple);

        tokens = reader.readLine().split("\\s+");
        Tuple<String, Integer> tuple1 = new Tuple(tokens[0], Integer.parseInt(tokens[1]));
        System.out.println(tuple1);

        tokens = reader.readLine().split("\\s+");
        Tuple<Integer, Double> tuple2 = new Tuple(tokens[0], Double.parseDouble(tokens[1]));
        System.out.println(tuple2);
    }
}
