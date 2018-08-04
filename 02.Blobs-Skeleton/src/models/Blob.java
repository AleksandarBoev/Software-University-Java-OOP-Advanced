package models;

import models.attacks.AbstractAttack;
import models.attacks.PutridFart;
import models.behaviuors.AbstractBehaviour;
import models.behaviuors.Aggressive;

public class Blob { //TODO get all methods out in an interface! And these basic things in an abstract class. And then have a Blob

    private String name;
    private long currentHealth;
    private long damage; //TODO should be long
    private AbstractBehaviour behavior; //TODO po-dobre s interface
    private AbstractAttack attack; //TODO samo interface-i!
    private int triggerCount;

    private int initialHealth;
    private int initialDamage;

    public Blob(String name, int health, int damage, AbstractBehaviour behavior, AbstractAttack attack) {
        this.name = name;
        this.currentHealth = health;
        this.damage = damage;
        this.behavior = behavior;
        this.attack = attack;

        this.initialHealth = health;
        this.initialDamage = damage;
    }

    public long getHealth() { //are these necessary?
        return this.currentHealth;
    }

    public void setHealth(long health) {
        this.currentHealth = health;

        if (this.currentHealth < 0) {
            this.currentHealth = 0;
        }

        if (this.currentHealth <= this.initialHealth / 2 && !this.behavior.isTriggered()) {
            this.triggerBehavior();
        }
    }

    public long getDamage() {
        return this.damage;
    }

    public void setDamage(long currentDamage) {
        this.damage = currentDamage;
    } //is this necessary?

    public void attack(Blob target) { //TODO bez instance of!
        if (this.attack instanceof PutridFart) {
            this.attackAffectTarget(this, target);
        }
    }

    public void takeDamage(long damage) {
        long currentHealth = this.getHealth();
        currentHealth -= damage;
        this.setHealth(currentHealth);
    }

    public void triggerBehavior() { //TODO bez instance of!
        if (this.behavior instanceof Aggressive) {
            if (this.behavior.isTriggered()) {
                ((Aggressive) this.behavior).setIsTriggered(true);
                this.applyAgressiveTriggerEffect();
            }
        }
    }

    public AbstractBehaviour getBehavior() {
        return this.behavior;
    }

    public String getName() {
        return this.name;
    }

    public void update() {
        if (this.behavior.isTriggered()) {
            if (this.behavior instanceof Aggressive) {
                if (this.behavior.isTriggered()) {
                    ((Aggressive) this.behavior).setIsTriggered(true);
                    this.applyAgressiveRecurrentEffect();
                }
            }
        }
    }

    @Override
    public String toString() {
        if (this.getHealth() <= 0) {
            return String.format("IBlob %s KILLED", this.getName());
        }

        return String.format("IBlob %s: %s HP, %s Damage", this.getName(), this.getHealth(), this.getDamage());
    }

    private void attackAffectSource(Blob source) {
        source.setHealth(source.getHealth() - source.getHealth() / 2);
    }

    private void attackAffectTarget(Blob source, Blob target) {
        target.takeDamage(source.getDamage() * 2);
    }



    private void applyAgressiveTriggerEffect() { //TODO bez konkretiki!
        this.setDamage(this.getDamage() * 2);
    }

    private void applyAgressiveRecurrentEffect() { //TODO bez konkretiki
        if (((Aggressive)this.behavior).getRecurrentEffectIsActive()) {
            ((Aggressive)this.behavior).setRecurrentEffectIsActive(false);
        } else {
            this.setDamage(this.getDamage() - 5);

            if (this.getDamage() <= this.initialHealth) {
                this.setDamage(this.initialDamage);
            }
        }
    }
}
