package onehitdungeon.heroes;

import onehitdungeon.interfaces.ArmorItem;
import onehitdungeon.interfaces.Hero;
import onehitdungeon.interfaces.OffhandItem;
import onehitdungeon.interfaces.WeaponItem;

public abstract class BaseHero implements Hero {
    //The Heroes are initialized with an name (String), a weapon (WeaponItem), an offhand (OffhandItem) and an armor (ArmorItem).
    private String name;
    private WeaponItem weaponItem;
    private OffhandItem offhand;
    private ArmorItem armor;
    private double gold;

    private int timesTrained; //a.k.a. hero's level
//TODO need to keep track of how many times the hero has trained! But how?

    protected BaseHero(String name) {
        this.setName(name);
        this.setWeaponItem(weaponItem);
        this.setOffhand(offhand);
        this.setArmor(armor);
        this.timesTrained = 1;
        this.gold = 0.0;
    }

    @Override
    public String getHeroClass() { //TODO should it return the simple name, or the simple name WITHOUT the word "Hero" in it?
        return this.getClass().getSimpleName();
    }

    @Override
    public Double getGold() {
        return this.gold;
    }

    @Override
    public void earnGold(Double gold) {
        this.gold += gold;
    }

    @Override
    public void payGold(Double gold) {
        this.gold -= gold;
    }

    @Override
    public WeaponItem getWeapon() {
        return this.weaponItem;
    }

    @Override
    public OffhandItem getOffhand() {
        return this.offhand;
    }

    @Override
    public ArmorItem getArmor() {
        return this.armor;
    }


    @Override
    public Double getTotalPriceForUpgrade() {
        return this.weaponItem.getPriceForUpgrade() + this.offhand.getPriceForUpgrade() + this.armor.getPriceForUpgrade();
    }

    @Override
    public String getName() {
        return this.name;
    }

//{heroName} – Lvl. {timesTrained + 1} Paladin
//* Mace – {weaponBattlePower} (BP)
//* Shield – {offhandBattlePower} (BP)
//* Cuirass – {armorBattlePower} (BP)
//####################
//Gold: {gold}
//Upgrade cost: {totalItemsPriceForUpgrade}
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s - Lvl. %d %s",
                this.name, this.timesTrained, this.getHeroClass().replace("Hero", ""))).append(System.lineSeparator());

        sb.append("* %s").append(String.format(" - %d (BP)", this.weaponItem.getBattlePower())).append(System.lineSeparator());
        sb.append("* %s").append(String.format(" - %d (BP)", this.offhand.getBattlePower())).append(System.lineSeparator());
        sb.append("* %s").append(String.format(" - %d (BP)", this.armor.getBattlePower())).append(System.lineSeparator());

        sb.append("####################").append(System.lineSeparator());

        sb.append(String.format("Gold: %.2f", this.getGold())).append(System.lineSeparator());

        //TODO maybe use BigInteger?
        Double totalItemsPriceForUpgrade =
                this.weaponItem.getPriceForUpgrade() + this.offhand.getPriceForUpgrade() + this.armor.getPriceForUpgrade();

        sb.append(String.format("Upgrade cost: %.2f", totalItemsPriceForUpgrade));

        return sb.toString();
    }

    private void setName(String name) {
        this.name = name;
    }

    protected void setWeaponItem(WeaponItem weaponItem) {
        this.weaponItem = weaponItem;
    }

    protected void setOffhand(OffhandItem offhand) {
        this.offhand = offhand;
    }

    protected void setArmor(ArmorItem armor) {
        this.armor = armor;
    }

    private void setTimesTrained(int timesTrained) {
        this.timesTrained = 1;
    }

    private void increaseTimesTrained() {
        this.timesTrained++;
    }
}
