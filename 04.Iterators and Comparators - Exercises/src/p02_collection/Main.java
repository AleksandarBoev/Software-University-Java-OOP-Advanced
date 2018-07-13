package p02_collection;

import p02_collection.ListyIterator;

import javax.naming.OperationNotSupportedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ListyIterator<String> listyIterator = null;

        //Create 1 2 3 4 5
        //Move
        //PrintAll
        //END

        String input; //TODO make this into a code snippet
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

                case "PrintAll":
                    listyIterator.printAll();
                    break;
            }
        }
        reader.close();
    }
}
