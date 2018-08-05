package models.attacks;

import models.Blob;

public class Blobplode extends AbstractAttack {
    public Blobplode(Blob attacker, Blob target) {
        super(attacker, target);
    }

    @Override
    public void execute() {
        //•	Blobplode - the blob loses half its current health (e.g. from 55 health loses 27 health = 28 health left)
        // and produces an attack with damage equal to double its own damage
        //o	The blob cannot fall below 1 health from attacking with Blobplode
        long attackerHealth = super.getAttacker().getHealth();
        attackerHealth -= attackerHealth / 2; // --> 55 - (55 / 2) = 55 - 27 = 28
        super.getAttacker().setHealth(attackerHealth);

        if (super.getAttacker().getHealth() == 0)
            super.getAttacker().setHealth(1);

        long attackerDamage = super.getAttacker().getDamage() * 2; //doubles the damage it has now, not just the initial damage
        long targetHealth = super.getTarget().getHealth();
        targetHealth -= attackerDamage;

        if (targetHealth < 0)
            targetHealth = 0;

        super.getTarget().setHealth(targetHealth);
    }
}
