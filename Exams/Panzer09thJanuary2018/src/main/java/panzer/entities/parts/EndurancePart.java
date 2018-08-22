package panzer.entities.parts;

import panzer.abstract_classes.BasePart;
import panzer.contracts.HitPointsModifyingPart;

import java.math.BigDecimal;

public class EndurancePart extends BasePart implements HitPointsModifyingPart {
    private int hitPointsModifier;

    public EndurancePart(String model, double weight, BigDecimal price, int hitPointsModifier) {
        super(model, weight, price);
        this.hitPointsModifier = hitPointsModifier;
    }

    @Override
    public int getHitPointsModifier() {
        return this.hitPointsModifier;
    }

    @Override
    public String toString() {
        //+{additionalParamValue} {additionalParam}
        return super.toString() + String.format("+%d %s", this.hitPointsModifier, "HitPoints");
    }
}
