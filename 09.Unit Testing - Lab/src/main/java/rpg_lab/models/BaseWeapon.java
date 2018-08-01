package rpg_lab.models;

import rpg_lab.contracts.Target;
import rpg_lab.contracts.Weapon;

public abstract class BaseWeapon implements Weapon, Comparable<BaseWeapon> { //could also add "BaseTarget"
    private int attackPoints;
    private int durabilityPoints;

    protected BaseWeapon(int attackPoints, int durabilityPoints) {
        this.attackPoints = attackPoints;
        this.durabilityPoints = durabilityPoints;
    }

    @Override
    public int getAttackPoints() {
        return this.attackPoints;
    }

    @Override
    public int getDurabilityPoints() {
        return this.durabilityPoints;
    }

    @Override
    public void attack(Target target) throws IllegalStateException {
        if (this.getDurabilityPoints() <= 0) {
            throw new IllegalStateException(String.format("%s is broken.", this.getClass().getSimpleName()));
        }

        target.takeAttack(this.getAttackPoints());
        this.lowerDurabilityByOne();
    }

    @Override
    public int compareTo(BaseWeapon anotherWeapon) {
        if (this.getClass().getSimpleName().equals(anotherWeapon.getClass().getSimpleName()) &&
                this.attackPoints == anotherWeapon.attackPoints && this.durabilityPoints == anotherWeapon.durabilityPoints) {
            return 0;
        } else {
            return Integer.compare(this.attackPoints + this.durabilityPoints, anotherWeapon.attackPoints + anotherWeapon.durabilityPoints);
        }
    }

    private void lowerDurabilityByOne() {
        this.durabilityPoints--;
    }
}
