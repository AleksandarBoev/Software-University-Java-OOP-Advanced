package rpg_lab.contracts;

public interface RpgCharacter {
    String getName();

    int getExperience();

    Weapon getCurrentWeapon();

    void attack(Target target);

    void addWeapon(Weapon weapon);

    Iterable<Weapon> getWeapons();
}
