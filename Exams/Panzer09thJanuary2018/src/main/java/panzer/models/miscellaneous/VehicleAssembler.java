package panzer.models.miscellaneous;

import panzer.contracts.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VehicleAssembler implements Assembler { //TODO to be tested in the Unit tests!
    private List<Part> arsenalParts;

    private List<Part> shellParts;

    private List<Part> enduranceParts;

    private List<Part> allParts;

    public VehicleAssembler() {
        this.arsenalParts = new ArrayList<>();
        this.shellParts = new ArrayList<>();
        this.enduranceParts = new ArrayList<>();
        this.allParts = new ArrayList<>();
    }

    //TODO there is a "-" instead of a "+". So test the sum result!
    @Override
    public double getTotalWeight() {
        return this.arsenalParts.stream().mapToDouble((x) -> x.getWeight()).sum()
                + this.shellParts.stream().mapToDouble((x) -> x.getWeight()).sum()
                + this.enduranceParts.stream().mapToDouble((x) -> x.getWeight()).sum();
    }

    @Override
    public BigDecimal getTotalPrice() {
        BigDecimal result = BigDecimal.ZERO;

        for (Part arsenalPart : this.arsenalParts) {
            result = result.add(arsenalPart.getPrice());
        }

        for (Part shellPart : this.shellParts) {
            result = result.add(shellPart.getPrice());
        }

        for (Part endurancePart : this.enduranceParts) {
            result = result.add(endurancePart.getPrice());
        }

        return result;
    }

    //TODO mapToInt used. Possible integer max value overflow for all three methods!
    @Override
    public long getTotalAttackModification() { //TODO not sure about this casting
        return this.arsenalParts.stream()
                .map(x -> (AttackModifyingPart)x)
                .mapToLong((x) -> x.getAttackModifier())
                .sum();
    }

    //TODO int overflow
    @Override
    public long getTotalDefenseModification() {
        return this.shellParts.stream()
                .map(x -> (DefenseModifyingPart)x)
                .mapToLong((x) -> x.getDefenseModifier())
                .sum();
    }

    //TODO int overflow
    @Override
    public long getTotalHitPointModification() {
        return this.enduranceParts.stream()
                .map(x -> (HitPointsModifyingPart)x)
                .mapToLong((x) -> x.getHitPointsModifier())
                .sum();
    }

    //TODO possible problem
    @Override
    public void addArsenalPart(Part arsenalPart) {
        this.arsenalParts.add(arsenalPart);
        this.allParts.add(arsenalPart);
    }

    @Override
    public void addShellPart(Part shellPart) {
        this.shellParts.add(shellPart);
        this.allParts.add(shellPart);
    }

    @Override
    public void addEndurancePart(Part endurancePart) {
        this.enduranceParts.add(endurancePart);
        this.allParts.add(endurancePart);
    }
}