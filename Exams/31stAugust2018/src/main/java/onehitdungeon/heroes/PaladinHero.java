package onehitdungeon.heroes;

import onehitdungeon.interfaces.ArmorItem;
import onehitdungeon.interfaces.OffhandItem;
import onehitdungeon.interfaces.WeaponItem;
import onehitdungeon.items.Armor;
import onehitdungeon.items.Offhand;
import onehitdungeon.items.Weapon;

public class PaladinHero extends BaseHero {
    public PaladinHero(String name) {
        super(name);
    }

    @Override
    public Integer getTotalBattlePower() {
        //Paladin – ((weaponBattlePower + offhandBattlePower + armorBattlePower) * 4) / 9

        return ((super.getWeapon().getBattlePower() + super.getOffhand().getBattlePower() + super.getArmor().getBattlePower()) * 4) / 9;
    }

    //•	Initialized with a weapon with the following property values:
    //o	battlePower – 20 (Integer)
    //o	priceForUpgrade – 10 (Double)
    @Override
    protected void setWeaponItem(WeaponItem weaponItem) {
        super.setWeaponItem(new Weapon(20, 10.0));
    }

    //•	Initialized with an offhand with the following property values:
    //o	battlePower – 10 (Integer)
    //o	priceForUpgrade – 10 (Double)
    @Override
    protected void setOffhand(OffhandItem offhand) {
        super.setOffhand(new Offhand(10, 10.0));
    }

    //•	Initialized with an armor with the following property values:
    //o	battlePower – 25 (Integer)
    //o	priceForUpgrade – 20 (Double)
    @Override
    protected void setArmor(ArmorItem armor) {
        super.setArmor(new Armor(25, 20.0));
    }

    @Override
    public String toString() {
        //Mace
        //Shield
        //Cuirass
        return String.format(super.toString(), "Mace", "Shield", "Cuirass");
    }
}
