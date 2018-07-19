package p03_coffeeMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        //TEN
        //TWENTY
        //TWENTY
        //Small Espresso
        //END
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        CoffeeMachine coffeeMachine = new CoffeeMachine();

        String input;
        while(!"END".equals(input = reader.readLine())) {
            String[] tokens = input.split(" ");
            if (tokens.length == 1) { //it's a coin
                coffeeMachine.insertCoin(tokens[0]);
            } else {
                String size = tokens[0];
                String type = tokens[1];

                coffeeMachine.buyCoffee(size, type);
            }
        }
        reader.close();

        Iterable<Coffee> coffeeIterable = coffeeMachine.coffeesSold();
        for (Coffee coffee : coffeeIterable) {
            System.out.println(coffee);
        }
    }
}
