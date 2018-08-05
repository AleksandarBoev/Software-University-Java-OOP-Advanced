package models.behaviuors;

import models.Blob;

public class Aggressive extends AbstractBehaviour {

    private static final int AGGRESSIVE_DAMAGE_MULTIPLY = 2;
    private static final int AGGRESSIVE_DAMAGE_DECREMENT = 5;

    private long sourceInitialDamage;

    public Aggressive() {
        super();
    }

    @Override
    public void trigger(Blob source) {
        this.sourceInitialDamage = source.getDamage();
        super.setIsTriggered(true);
        this.applyTriggerEffect(source);
    }

    @Override
    public void applyRecurrentEffect(Blob source) {
        if (super.getRecurrentEffectIsActive()) { //TODO shouldn't it be the other way around?
            super.setRecurrentEffectIsActive(false);
        } else {
            source.setDamage(source.getDamage() - AGGRESSIVE_DAMAGE_DECREMENT);

            if (source.getDamage() <= this.sourceInitialDamage) {
                source.setDamage(this.sourceInitialDamage);
            }
        }
    }

    @Override
    public boolean getRecurrentEffectIsActive() {
        return super.recurrentEffectIsActive;
    }

    @Override
    public void setRecurrentEffectIsActive(boolean recurrentEffectIsActive){
        super.recurrentEffectIsActive = recurrentEffectIsActive;
    }

    @Override
    public void applyTriggerEffect(Blob source) { //one time event
        source.setDamage(source.getDamage() * AGGRESSIVE_DAMAGE_MULTIPLY);
    }
}
