package onehitdungeon.heroes;

import onehitdungeon.interfaces.ArmorItem;
import onehitdungeon.interfaces.OffhandItem;
import onehitdungeon.interfaces.WeaponItem;
import onehitdungeon.items.Armor;
import onehitdungeon.items.Offhand;
import onehitdungeon.items.Weapon;

public class MageHero extends BaseHero {
    public MageHero(String name) {
        super(name);
    }

    @Override
    public Integer getTotalBattlePower() {
        //Mage – ((weaponBattlePower + armorBattlePower - offhandBattlePower) * 3) / 4
        return ((super.getWeapon().getBattlePower() + super.getArmor().getBattlePower() - super.getOffhand().getBattlePower()) * 3) / 4;
    }

    //•	Initialized with a weapon with the following property values:
    //o	battlePower – 45 (Integer)
    //o	priceForUpgrade – 15 (Double)
    @Override
    protected void setWeaponItem(WeaponItem weaponItem) {
        super.setWeaponItem(new Weapon(45, 15.0));
    }

    //•	Initialized with a offhand with the following property values:
    //o	battlePower – 25 (Integer)
    //o	priceForUpgrade – 20 (Double)
    @Override
    protected void setOffhand(OffhandItem offhand) {
        super.setOffhand(new Offhand(25, 20.0));
    }

    //•	Initialized with a armor with the following property values:
    //o	battlePower – 10 (Integer)
    //o	priceForUpgrade – 25 (Double)
    @Override
    protected void setArmor(ArmorItem armor) {
        super.setArmor(new Armor(10, 25.0));
    }

    //* Staff
    //* Orb
    //* Cape
    @Override
    public String toString() {
        return String.format(super.toString(), "Staff", "Orb", "Cape");
    }
}
