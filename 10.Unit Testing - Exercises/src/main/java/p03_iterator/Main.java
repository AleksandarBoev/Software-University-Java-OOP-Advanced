package p03_iterator;

import javax.naming.OperationNotSupportedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    @SuppressWarnings("ConstantConditions") //stops warnings like "blabla can cause java.lang.NullPointerException"
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ListIterator<String> listIterator = null;

        String input;
        while(!"END".equals(input = reader.readLine())) { //usually a command factory and an engine would be used.
            String[] tokens = input.split(" ");

            String command = tokens[0];
            switch (command) {
                case "Create":
                    try {
                        listIterator = new ListIteratorImpl<>(Arrays.asList(Arrays.stream(tokens).skip(1).toArray(n -> new String[n])));
                    } catch (OperationNotSupportedException onse) {
                        System.out.println(onse.getMessage());
                        return;
                    }
                    break;

                case "Print":
                    try {
                        System.out.println(listIterator.getCurrentElementAsString());
                    } catch (IllegalStateException ise) {
                        System.out.println(ise.getMessage());
                        return;
                    }
                    break;

                case "HasNext":
                    System.out.println(listIterator.hasNext());
                    break;

                case "Move":
                    try {
                        System.out.println(listIterator.move());
                    } catch (IllegalStateException ise) {
                        System.out.println(ise.getMessage());
                        return;
                    }
                    break;
            }
        }

        reader.close();
    }
}
