package p11_threeuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = reader.readLine().split("\\s+");
        String firstVariable = tokens[0] + " " + tokens[1];
        String secondVariable = tokens[2];
        String thirdVariable = tokens[3];
        Threeuple<String, String, String> threeuple = new Threeuple<>(firstVariable, secondVariable, thirdVariable);
        System.out.println(threeuple);

        tokens = reader.readLine().split("\\s+");
        Threeuple<String, Integer, Boolean> threeuple1 = new Threeuple<>(tokens[0], Integer.parseInt(tokens[1]), parseBoolean(tokens[2]));
        System.out.println(threeuple1);

        tokens = reader.readLine().split("\\s+");
        Threeuple<String, Double, String> threeuple2 = new Threeuple<>(tokens[0], Double.parseDouble(tokens[1]), tokens[2]);
        System.out.println(threeuple2);
    }

    private static Boolean parseBoolean(String word) {
        return word.equals("drunk");
    }
}
