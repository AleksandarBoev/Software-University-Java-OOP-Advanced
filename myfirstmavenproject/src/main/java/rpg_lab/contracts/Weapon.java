package rpg_lab.contracts;

public interface Weapon {

    int getAttackPoints();

    int getDurabilityPoints();

    void attack(Target target) throws IllegalStateException;
}
