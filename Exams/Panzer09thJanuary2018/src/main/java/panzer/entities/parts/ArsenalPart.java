package panzer.entities.parts;

import panzer.abstract_classes.BasePart;
import panzer.contracts.AttackModifyingPart;

import java.math.BigDecimal;

public class ArsenalPart extends BasePart implements AttackModifyingPart {
    private int attackModifier;

    public ArsenalPart(String model, double weight, BigDecimal price, int attackModifier) {
        super(model, weight, price);
        this.attackModifier = attackModifier;
    }

    @Override
    public int getAttackModifier() {
        return this.attackModifier;
    }

    @Override
    public String toString() {
        //+{additionalParamValue} {additionalParam}
        return super.toString() + String.format("+%d %s", this.attackModifier, "Attack");
    }
}
