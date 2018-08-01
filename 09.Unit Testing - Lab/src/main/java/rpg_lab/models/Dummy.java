package rpg_lab.models;

import rpg_lab.contracts.Target;
import rpg_lab.contracts.Weapon;
import rpg_lab.contracts.WeaponFactory;

public class Dummy implements Target {
    private int health;
    private int experience;
    private boolean droppedWeapon;

    public Dummy(int health, int experience) {
        this.health = health;
        this.experience = experience;

        this.droppedWeapon = false;
    }

    public int getHealth() {
        return this.health;
    }

    public void takeAttack(int attackPoints) {
        if (this.isDead()) {
            throw new IllegalStateException("Dummy is dead.");
        }

        this.health -= attackPoints;
    }

    public int giveExperience() {
        if (!this.isDead()) {
            throw new IllegalStateException("Target is not dead.");
        }

        return this.experience;
    }

    public boolean isDead() {
        return this.health <= 0;
    }

    @Override
    public Weapon dropRandomWeapon() {
        if (this.isDead() && !this.droppedWeapon) {
            WeaponFactory weaponFactory = new WeaponFactoryImpl();
            return weaponFactory.createRandomWeaponWithRandomStats();
        } else if (!this.isDead()) {
            throw new IllegalStateException("Target is not dead.");
        } else {
            throw new IllegalStateException("Target has already dropped a weapon!");
        }
    }
}
