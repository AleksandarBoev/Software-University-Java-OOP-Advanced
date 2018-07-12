package p06_genericCountMethodDouble;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Box<Double> box = new Box();
        int numberOfInputs = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numberOfInputs; i++) {
            box.add(Double.valueOf((reader.readLine())));
        }
        Double doubleToBeCompared = Double.valueOf((reader.readLine()));
        reader.close();

        System.out.println(box.getBiggerItemsCount(doubleToBeCompared));
    }
}
