package panzer.abstract_classes;

import panzer.contracts.Assembler;
import panzer.contracts.Part;
import panzer.contracts.Vehicle;
import panzer.models.miscellaneous.VehicleAssembler;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public abstract class BaseVehicle implements Vehicle {
    private String model;
    private double weight;
    private BigDecimal price;
    private int attack;
    private int defence;
    private int hitPoints;

    private Assembler assembler;

    protected BaseVehicle(String model, double weight, BigDecimal price, int attack, int defence, int hitPoints) {
        this.model = model;
        this.weight = weight;
        this.price = price;
        this.attack = attack;
        this.defence = defence;
        this.hitPoints = hitPoints;
        this.assembler = new VehicleAssembler();
    }

    protected double getWeight() {
        return this.weight;
    }

    protected BigDecimal getPrice() {
        return this.price;
    }

    protected int getAttack() {
        return this.attack;
    }

    protected int getDefence() {
        return this.defence;
    }

    protected int getHitPoints() {
        return this.hitPoints;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public double getTotalWeight() {
        return this.assembler.getTotalWeight();
    }

    @Override
    public BigDecimal getTotalPrice() {
        return this.assembler.getTotalPrice();
    }

    @Override
    public long getTotalAttack() {
        return this.assembler.getTotalAttackModification();
    }

    @Override
    public long getTotalDefense() {
        return this.assembler.getTotalDefenseModification();
    }

    @Override
    public long getTotalHitPoints() {
        return this.assembler.getTotalHitPointModification();
    }

    @Override
    public void addArsenalPart(Part arsenalPart) {
        this.assembler.addArsenalPart(arsenalPart);
    }

    @Override
    public void addShellPart(Part shellPart) {
        this.assembler.addShellPart(shellPart);
    }

    @Override
    public void addEndurancePart(Part endurancePart) {
        this.assembler.addEndurancePart(endurancePart);
    }

    @Override
    public Iterable<Part> getParts() {
        Iterable<Part> allParts = new ArrayList<>();

        try {
            Field allPartsField = this.assembler.getClass().getDeclaredField("allParts");
            allPartsField.setAccessible(true);

            allParts = (Iterable<Part>) allPartsField.get(this.assembler);

            allPartsField.setAccessible(false);
        } catch (Exception e) {

        }

        return allParts;
    }

    //{vehicleType} – {vehicleModel}
    //Total Weight: {totalWeight}
    //Total Price: {totalPrice}
    //Attack: {totalAttack}
    //Defense: {totalDefense}
    //HitPoints: {totalHitPoints}
    //Parts: {part1Model}, {part2Model}...
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(" - ").append(this.model).append(System.lineSeparator());
        sb.append("Total Weight: ").append(String.format("%.3f", this.getTotalWeight())).append(System.lineSeparator());

        BigDecimal totalPrice = this.getTotalPrice().setScale(3, RoundingMode.HALF_UP);
        sb.append("Total Price: ").append(String.format("%s", totalPrice)).append(System.lineSeparator());

        sb.append("Attack: ").append(this.getTotalAttack()).append(System.lineSeparator());
        sb.append("Defense: ").append(this.getTotalDefense()).append(System.lineSeparator());
        sb.append("HitPoints: ").append(this.getTotalHitPoints()).append(System.lineSeparator());
        sb.append("Parts: ");

        StringBuilder partsString = new StringBuilder();
        for (Part part : this.getParts()) {
            partsString.append(part.getModel()).append(", ");
        }

        if ("".equals(partsString.toString())) {
            sb.append("None");
        } else {
            partsString.delete(partsString.length() - 2, partsString.length());
            sb.append(partsString.toString());
        }

        return sb.toString();
    }
}
