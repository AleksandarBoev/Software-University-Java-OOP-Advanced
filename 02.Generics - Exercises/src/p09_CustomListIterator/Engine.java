package p09_CustomListIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine {
    private CustomList<String> customList;

    public Engine() {
        this.customList = new CustomList();
    }

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

                case "Sort":
                    Sorter.sort(this.customList);
                    break;
            }
        }

        reader.close();
    }
}
