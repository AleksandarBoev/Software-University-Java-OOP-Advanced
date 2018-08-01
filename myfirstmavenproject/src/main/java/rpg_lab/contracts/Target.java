package rpg_lab.contracts;

public interface Target {
    int getHealth();

    void takeAttack(int attackPoints);

    int giveExperience();

    boolean isDead();

    public Weapon dropRandomWeapon();

    }
