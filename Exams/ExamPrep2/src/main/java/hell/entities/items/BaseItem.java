package hell.entities.items;

import hell.interfaces.Item;

public abstract class BaseItem implements Item {
    private String name;
    private int strengthBonus;
    private int agilityBonus;
    private int intelligenceBonus;
    private int hitPointsBonus;
    private int damageBonus;

    protected BaseItem(String name, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitPointsBonus, int damageBonus) {
        this.name = name;
        this.strengthBonus = strengthBonus;
        this.agilityBonus = agilityBonus;
        this.intelligenceBonus = intelligenceBonus;
        this.hitPointsBonus = hitPointsBonus;
        this.damageBonus = damageBonus;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getStrengthBonus() {
        return this.strengthBonus;
    }

    @Override
    public int getAgilityBonus() {
        return this.agilityBonus;
    }

    @Override
    public int getIntelligenceBonus() {
        return this.intelligenceBonus;
    }

    @Override
    public int getHitPointsBonus() {
        return this.hitPointsBonus;
    }

    @Override
    public int getDamageBonus() {
        return this.damageBonus;
    }

    @Override
    public String toString() {
        //###Item: Spear
        //###+25 Strength
        //###+10 Agility
        //###+10 Intelligence
        //###+100 HitPoints
        //###+50 Damage
        return String.format(
                "###Item: %s%n" +
                "###+%d Strength%n" +
                        "###+%d Agility%n" +
                        "###+%d Intelligence%n" +
                        "###+%d HitPoints%n" +
                        "###+%d Damage",
                this.name, this.strengthBonus, this.agilityBonus, this.intelligenceBonus, this.hitPointsBonus, this.damageBonus);
    }
}
