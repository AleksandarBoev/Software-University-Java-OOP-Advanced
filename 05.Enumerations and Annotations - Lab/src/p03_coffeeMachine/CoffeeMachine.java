package p03_coffeeMachine;

import java.util.ArrayList;
import java.util.Collection;

public class CoffeeMachine {
    Collection<Coin> coins;
    Collection<Coffee> coffees;

    public CoffeeMachine() {
        this.coins = new ArrayList<>();
        this.coffees = new ArrayList<>();
    }

    public void buyCoffee(String size, String type) {
        CoffeeSize coffeeSize = CoffeeSize.valueOf(CoffeeSize.class, size.toUpperCase());

        if (coffeeSize.getPrice() > this.getTotalMoney())
            return;

        CoffeeType coffeeType = CoffeeType.valueOf(CoffeeType.class, type.toUpperCase());
        Coffee coffee = new Coffee(coffeeType, coffeeSize);
        this.coffees.add(coffee);
        this.coins.clear();
    }

    public void insertCoin(String coin) {
        Coin currentCoin = Coin.valueOf(Coin.class, coin.toUpperCase());
        this.coins.add(currentCoin);
    }

    public Iterable<Coffee> coffeesSold() {
        return this.coffees;
    }

    private int getTotalMoney() {
        return this.coins.stream().mapToInt(x -> x.getValue()).sum();
    }
}
