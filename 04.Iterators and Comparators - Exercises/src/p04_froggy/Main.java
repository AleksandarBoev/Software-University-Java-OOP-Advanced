package p04_froggy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine().trim();
        if ("".equals(input)) {
            return;
        }
        Integer[] jumpNumbers = Arrays.stream(input.split("[, ]+"))
                .map(x -> Integer.parseInt(x))
                .toArray(n -> new Integer[n]);

        Lake lake = new Lake(jumpNumbers);
        StringBuilder answer = new StringBuilder();
        for (Object o : lake) {
            answer.append(o).append(", ");
        }

        answer.delete(answer.length() - 2, answer.length());
        System.out.println(answer);
    }
}
