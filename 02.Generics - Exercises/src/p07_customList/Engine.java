package p07_customList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine {
    private CustomList<String> customList;

    public Engine() {
        this.customList = new CustomList();
    }

    //•	Add <element> - Adds the given element to the end of the list
    //•	Remove <index> - Removes the element at the given index
    //•	Contains <element> - Prints if the list contains the given element (true or false)
    //•	Swap <index> <index> - Swaps the elements at the given indexes
    //•	Greater <element> - Counts the elements that are greater than the given element and prints their count
    //•	Max - Prints the maximum element in the list
    //•	Min - Prints the minimum element in the list
    //•	Print - Prints all elements in the list, each on a separate line
    //•	END - stops the reading of commands

    public void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while (!"END".equals(input = reader.readLine())) {
            String[] tokens = input.split(" ");
            String command = tokens[0];
            switch (command) {
                case "Add":
                    this.customList.add(tokens[1]);
                    break;

                case "Remove":
                    this.customList.remove(Integer.parseInt(tokens[1]));
                    break;

                case "Contains":
                    System.out.println(this.customList.contains(tokens[1]));
                    break;

                case "Swap":
                    this.customList.swap(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                    break;

                case "Greater":
                    System.out.println(this.customList.countGreaterThan(tokens[1]));
                    break;

                case "Max":
                    System.out.println(this.customList.getMax());
                    break;

                case "Min":
                    System.out.println(this.customList.getMin());
                    break;

                case "Print":
                    System.out.println(this.customList);
                    break;
            }
        }

        reader.close();
    }
}
