package p05_genericCountMethodString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Box<String> box = new Box();
        int numberOfInputs = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numberOfInputs; i++) {
            box.add(reader.readLine());
        }
        String stringToBeCompared = reader.readLine();
        reader.close();

        System.out.println(box.getBiggerItemsCount(stringToBeCompared));
    }
}
