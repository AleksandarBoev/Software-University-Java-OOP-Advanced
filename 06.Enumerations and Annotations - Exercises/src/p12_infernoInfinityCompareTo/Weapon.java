package p12_infernoInfinityCompareTo;

import java.util.Comparator;

public class Weapon implements Comparable<Weapon> {
    private static int STRENGTH_BONUS_TO_MIN_DAMAGE = 2;
    private static int STRENGTH_BONUS_TO_MAX_DAMAGE = 3;
    private static int AGILITY_BONUS_TO_MIN_DAMAGE = 1;
    private static int AGILITY_BONUS_TO_MAX_DAMAGE = 4;
    private static int VITALITY_BONUS_TO_MIN_DAMAGE = 0;
    private static int VITALITY_BONUS_TO_MAX_DAMAGE = 0;
    private static final int ZERO = 0;


    private String name;
    private WeaponType weaponType;
    private Gem[] gems;

    private int additionalStrength;
    private int additionalAgility;
    private int additionalVitality;
    private int minDamage;
    private int maxDamage;

    public Weapon(String name, String weaponType) {
        this.name = name;
        this.weaponType = WeaponType.valueOf(WeaponType.class, weaponType.toUpperCase());
        this.gems = new Gem[this.weaponType.getGemCount()];

        this.additionalStrength = ZERO;
        this.additionalAgility = ZERO;
        this.additionalVitality = ZERO;
        this.minDamage = this.weaponType.getMinDamage();
        this.maxDamage = this.weaponType.getMaxDamage();
    }

    @Override
    public String toString() {
        return String.format("%s: %d-%d Damage, +%d Strength, +%d Agility, +%d Vitality", this.name, this.minDamage, this.maxDamage,
                this.additionalStrength, this.additionalAgility, this.additionalVitality);
    }

    public String toStringAfterComparison() {
        return String.format("%s: %d-%d Damage, +%d Strength, +%d Agility, +%d Vitality (Item Level: %.1f)", this.name, this.minDamage, this.maxDamage,
                this.additionalStrength, this.additionalAgility, this.additionalVitality, this.calculateItemLevel());
    }



    public void addGem(int socketIndex, String gemType) {
        if (this.indexIsOutOfBounds(socketIndex))
            return;

        Gem gem = Gem.valueOf(Gem.class, gemType.toUpperCase());
        this.gems[socketIndex] = gem;

        this.updateAdditionalAttributes();
        this.updateMinMaxDamage();
    }

    public void removeGem(int socketIndex) {
        if (this.indexIsOutOfBounds(socketIndex))
            return;

        this.gems[socketIndex] = null;

        this.updateAdditionalAttributes();
        this.updateMinMaxDamage();
    }

    private boolean indexIsOutOfBounds(int index) {
        return index < 0 || index >= this.weaponType.getGemCount();
    }

    private void updateAdditionalAttributes() {
        this.additionalStrength = 0;
        this.additionalAgility = 0;
        this.additionalVitality = 0;

        for (Gem gem : this.gems) {
            if (gem == null)
                continue;

            this.additionalStrength += gem.getStrength();
            this.additionalAgility += gem.getAgility();
            this.additionalVitality += gem.getVitality();
        }
    }

    private void updateMinMaxDamage() {
        this.minDamage = this.weaponType.getMinDamage();
        this.maxDamage = this.weaponType.getMaxDamage();

        this.minDamage += this.additionalStrength * STRENGTH_BONUS_TO_MIN_DAMAGE
                + this.additionalAgility * AGILITY_BONUS_TO_MIN_DAMAGE
                + this.additionalVitality * VITALITY_BONUS_TO_MIN_DAMAGE;

        this.maxDamage += this.additionalStrength * STRENGTH_BONUS_TO_MAX_DAMAGE
                + this.additionalAgility * AGILITY_BONUS_TO_MAX_DAMAGE
                + this.additionalVitality * VITALITY_BONUS_TO_MAX_DAMAGE;
    }

    private double calculateItemLevel() {
        return (this.minDamage + this.maxDamage) / 2.0 + this.additionalStrength + this.additionalAgility + this.additionalVitality;
    }

    @Override
    public int compareTo(Weapon anotherWeapon) {
        return Double.compare(this.calculateItemLevel(), anotherWeapon.calculateItemLevel());
    }
}
