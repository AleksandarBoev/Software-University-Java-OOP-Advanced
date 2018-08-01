package rpg_lab.models;

import rpg_lab.contracts.RpgCharacter;
import rpg_lab.contracts.Target;
import rpg_lab.contracts.Weapon;

import java.util.ArrayList;
import java.util.Collection;

public class Hero implements RpgCharacter {

    private String name;
    private int experience;
//    private Axe currentWeapon;
    private Weapon currentWeapon;
    private Collection<Weapon> weapons;

    public Hero(String name, Weapon currentWeapon) {
        this.name = name;
        this.experience = 0;
        this.currentWeapon = currentWeapon;
        this.weapons = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getExperience() {
        return this.experience;
    }

    @Override
    public Weapon getCurrentWeapon() {
        return this.currentWeapon;
    }

    @Override
    public void attack(Target target) {
        this.currentWeapon.attack(target);

        if (target.isDead()) {
            this.experience += target.giveExperience();
        }
    }

    @Override
    public void addWeapon(Weapon weapon) {
        this.weapons.add(weapon);
    }

    @Override
    public Iterable<Weapon> getWeapons() {
        return this.weapons;
    }
}
