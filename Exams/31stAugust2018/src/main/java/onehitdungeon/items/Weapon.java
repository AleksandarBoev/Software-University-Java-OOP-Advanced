package onehitdungeon.items;

import onehitdungeon.interfaces.WeaponItem;

public class Weapon extends BaseItem implements WeaponItem {
    public Weapon(Integer battlePower, Double priceForUpgrade) {
        super(battlePower, priceForUpgrade);
    }
}
