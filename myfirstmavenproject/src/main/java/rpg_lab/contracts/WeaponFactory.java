package rpg_lab.contracts;

public interface WeaponFactory {
    Weapon createWeapon(String[] data);

    Weapon createRandomWeaponWithRandomStats();
}
