package panzer.entities.vehicles;

import panzer.abstract_classes.BaseVehicle;

import java.math.BigDecimal;

public class Revenger extends BaseVehicle {
    public Revenger(String model, double weight, BigDecimal price, int attack, int defence, int hitPoints) {
        super(model, weight, price, attack, defence, hitPoints);
    }

    //•	Price – increases its given price by 50%.
    //•	Attack – increases its given attack by 150%.
    //•	Defense – decreases its given defense by 50%.
    //•	HitPoints – decreases its given hitPoints by 50%.

    //FIRST increase/decrease the BASE stat, after that add the bonuses from the parts!
    //if I knew that earlier it would have been easier to do it via setters in the constructor
    @Override
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = super.getPrice();
        totalPrice = totalPrice.add(totalPrice.multiply(BigDecimal.valueOf(0.5)));
        totalPrice = totalPrice.add(super.getTotalPrice());
        return totalPrice;
    }

    @Override
    public double getTotalWeight() {
        return super.getTotalWeight() + super.getWeight();
    }

    @Override
    public long getTotalAttack() {
        long totalAttack = super.getAttack();
        totalAttack += totalAttack * 150 / 100;
        totalAttack += super.getTotalAttack();
        return totalAttack;

        //formula for calculating percentage of whole numbers
        //Note: 50% of X is EQUAL to (X * 50) / 100.
        //Note: Decrease means DECREASE… 100 decreased by 25% = 100 – (100 * 25) / 100 = 100 – 25 = 75.
    }

    @Override
    public long getTotalDefense() {
        long totalDefence = super.getDefence();
        totalDefence -= totalDefence * 50 / 100;
        totalDefence += super.getTotalDefense();
        return totalDefence;
    }

    @Override
    public long getTotalHitPoints() {
        long totalHitPoints = super.getHitPoints();
        totalHitPoints -= totalHitPoints * 50 / 100;
        totalHitPoints += super.getTotalHitPoints();
        return totalHitPoints;
    }
}
