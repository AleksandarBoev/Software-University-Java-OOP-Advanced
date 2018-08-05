package interfaces;

import models.Blob;

public interface Behaviour {

    boolean isTriggered();

    void setIsTriggered(boolean isTriggered);

    boolean getRecurrentEffectIsActive();

    void setRecurrentEffectIsActive(boolean recurrentEffectIsActive);

    void trigger(Blob source);

    void applyRecurrentEffect(Blob source);

    void applyTriggerEffect(Blob source);
}
