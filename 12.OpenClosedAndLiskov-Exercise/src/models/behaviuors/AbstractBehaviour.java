package models.behaviuors;

import interfaces.Behaviour;
import models.Blob;

public abstract class AbstractBehaviour implements Behaviour {
    //TODO a tova kakvo shte bude? Purvo premesti vsichki metodi v interface-a

    protected boolean isTriggered;
    protected boolean recurrentEffectIsActive;

    public AbstractBehaviour() {
        this.recurrentEffectIsActive = true;
    }

    public boolean isTriggered() {
        return this.isTriggered;
    }

    public void setIsTriggered(boolean isTriggered) {
        this.isTriggered = isTriggered;
    }

    public boolean getRecurrentEffectIsActive() {
        return this.recurrentEffectIsActive;
    }

    public void setRecurrentEffectIsActive(boolean recurrentEffectIsActive){
        this.recurrentEffectIsActive = recurrentEffectIsActive;
    }
}
