package panzer.entities.parts;

import panzer.abstract_classes.BasePart;
import panzer.contracts.DefenseModifyingPart;

import java.math.BigDecimal;

public class ShellPart extends BasePart implements DefenseModifyingPart {
    private int defenseModifier;

    public ShellPart(String model, double weight, BigDecimal price, int defenseModifier) {
        super(model, weight, price);
        this.defenseModifier = defenseModifier;
    }

    @Override
    public int getDefenseModifier() {
        return this.defenseModifier;
    }

    @Override
    public String toString() {
        //+{additionalParamValue} {additionalParam}
        return super.toString() + String.format("+%d %s", this.defenseModifier, "Defense");
    }
}
