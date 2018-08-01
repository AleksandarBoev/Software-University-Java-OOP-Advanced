package rpg_lab.models;

import rpg_lab.contracts.Weapon;
import rpg_lab.contracts.WeaponFactory;

import java.util.Random;

public class WeaponFactoryImpl implements WeaponFactory {
    private static final int UPPER_LIMIT_ATTACK_POINTS = 10;
    private static final int UPPER_LIMIT_DURABILITY_POINTS = 10;
    private static final String[] WEAPON_TYPES = {"sword", "axe", "mace"};

    @Override
    public Weapon createWeapon(String[] data) {
        String weaponType = data[0];
        int attackPoints = Integer.parseInt(data[1]);
        int durabilityPoints = Integer.parseInt(data[2]);

        switch (weaponType.toLowerCase()) {
            case "axe":
                return new Axe(attackPoints, durabilityPoints);

            case "sword":
                return new Sword(attackPoints, durabilityPoints);

            case "mace":
                return new Mace(attackPoints, durabilityPoints);

                default:
                    return null;
        }
    }

    @Override
    public Weapon createRandomWeaponWithRandomStats() {
        Random random = new Random();

        int randomWeaponTypeNumber = random.nextInt(WEAPON_TYPES.length);
        String randomWeaponType = WEAPON_TYPES[randomWeaponTypeNumber];

        int randomAttackPoints = random.nextInt(UPPER_LIMIT_ATTACK_POINTS);
        int randomDurabilityPoints = random.nextInt(UPPER_LIMIT_DURABILITY_POINTS);

        return this.createWeapon(new String[]{randomWeaponType, "" + randomAttackPoints, "" + randomDurabilityPoints}); //weird way of doing things...
    }

}
