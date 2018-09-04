package onehitdungeon.items;

import onehitdungeon.interfaces.ArmorItem;

public class Armor extends BaseItem implements ArmorItem {
    public Armor(Integer battlePower, Double priceForUpgrade) {
        super(battlePower, priceForUpgrade);
    }
}
