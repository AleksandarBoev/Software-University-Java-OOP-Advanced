package p03_stackIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Engine {
    private Stack<Integer> stack;

    public Engine() {
        this.stack = new StackImpl();
    }

    public void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while(!"END".equals(input = reader.readLine())) {
            String[] tokens = input.split(" ", 2);

            String command = tokens[0];
            switch (command) {
                case "Push":
                    Integer[] values = Arrays.stream(tokens[1].split("[, ]+")).map(x -> Integer.parseInt(x)).toArray(n -> new Integer[n]);
                    this.stack.push(values);
                    break;

                case "Pop":
                    try {
                        this.stack.pop();
                    } catch (NoSuchElementException nsee) {
                        System.out.println(nsee.getMessage());
                    }
                    break;
            }
        }
        reader.close();

        for (Integer integer : this.stack) {
            System.out.println(integer);
        }

        for (Integer integer : this.stack) {
            System.out.println(integer);
        }
    }
}
