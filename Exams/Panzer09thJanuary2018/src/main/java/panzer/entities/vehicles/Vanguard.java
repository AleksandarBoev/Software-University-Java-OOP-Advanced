package panzer.entities.vehicles;

import panzer.abstract_classes.BaseVehicle;

import java.math.BigDecimal;

public class Vanguard extends BaseVehicle {
    public Vanguard(String model, double weight, BigDecimal price, int attack, int defence, int hitPoints) {
        super(model, weight, price, attack, defence, hitPoints);
    }

    //•	Weight – increases its given weight by 100%.
    //•	Attack – decreases its given attack by 25%.
    //•	Defense – increases its given defense by 50%.
    //•	HitPoints – increases its given hitPoints by 75%.

    @Override
    public BigDecimal getTotalPrice() {
        return super.getTotalPrice().add(super.getPrice());
    }

    @Override
    public double getTotalWeight() {
        return super.getWeight() * 2.0 + super.getTotalWeight();
    }

    @Override
    public long getTotalAttack() {
        long totalAttack = super.getAttack();
        totalAttack -= totalAttack * 25 / 100; //1450 - (1450 / 4)
        totalAttack += super.getTotalAttack();
        return totalAttack;

        //formula for calculating percentage of whole numbers
        //Note: 50% of X is EQUAL to (X * 50) / 100.
        //Note: Decrease means DECREASE… 100 decreased by 25% = 100 – (100 * 25) / 100 = 100 – 25 = 75.
    }

    @Override
    public long getTotalDefense() {
        long totalDefence = super.getDefence();
        totalDefence += totalDefence * 50 / 100;
        totalDefence += super.getTotalDefense();
        return totalDefence;
    }

    @Override
    public long getTotalHitPoints() {
        long totalHitPoints = super.getHitPoints();
        totalHitPoints += totalHitPoints * 75 / 100;
        totalHitPoints += super.getTotalHitPoints();
        return totalHitPoints;
    }
}
