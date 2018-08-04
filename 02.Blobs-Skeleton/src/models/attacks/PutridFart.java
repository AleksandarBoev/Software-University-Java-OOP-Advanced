package models.attacks;

import models.Blob;

public class PutridFart extends AbstractAttack {

    public PutridFart(Blob attacker, Blob target) {
        super(attacker, target);
    }

    @Override
    public void execute() {
        long attackerDamage = super.getAttacker().getDamage();
        long targetHealth = super.getTarget().getHealth();

        long result = targetHealth - attackerDamage;
        if (targetHealth < 0)
            targetHealth = 0;

        super.getTarget().setHealth(targetHealth);
    }
}
