package p01_listyIterator;

import javax.naming.OperationNotSupportedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException { //not a good idea to put logic in the Main method..., but it's anoying
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ListyIterator<String> listyIterator = null;

        String input;
        while (!"END".equals(input = reader.readLine())) {
            String[] tokens = input.split(" ");
            String command = tokens[0];

            switch (command) {
                case "Create":
                    String[] varargs = Arrays.stream(tokens).skip(1).toArray(n -> new String[n]);
                    listyIterator = new ListyIterator<>(varargs);
                    break;

                case "HasNext":
                    System.out.println(listyIterator.hasNext());
                    break;

                case "Print":
                    try {
                        listyIterator.print();
                    } catch (OperationNotSupportedException onse) {
                        System.out.println(onse.getMessage());
                    }
                    break;

                case "Move":
                    System.out.println(listyIterator.move());
                    break;
            }
        }
        reader.close();

    }
}
