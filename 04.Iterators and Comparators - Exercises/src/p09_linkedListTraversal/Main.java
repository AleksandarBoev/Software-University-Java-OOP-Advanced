package p09_linkedListTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        //6
        //Add 13
        //Add 4
        //Add 20
        //Add 4
        //Remove 4
        //Add 4
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MyLinkedList<Integer> integerMyLinkedList = new MyLinkedList<>();
        int numberOfInputs = Integer.parseInt(reader.readLine());

        for (int i = 0; i < numberOfInputs; i++) {
            String[] tokens = reader.readLine().split(" ");
            String command = tokens[0];
            Integer value = Integer.valueOf(tokens[1]);

            if ("Add".equals(command)) {
                integerMyLinkedList.add(value);
            } else {
                integerMyLinkedList.remove(value);
            }
        }
        reader.close();

        System.out.println(integerMyLinkedList.getSize());

        for (Integer integer : integerMyLinkedList) {
            System.out.print(integer + " ");
        }
    }
}
