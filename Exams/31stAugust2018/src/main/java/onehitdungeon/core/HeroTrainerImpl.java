package onehitdungeon.core;

import onehitdungeon.interfaces.Hero;
import onehitdungeon.interfaces.HeroTrainer;
import onehitdungeon.interfaces.Item;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class HeroTrainerImpl implements HeroTrainer {
    private Map<String, Integer> itemTrainProperties = new HashMap<>() {{
        //TODO test all upgrades! idea: test with easy to test mocked values, like 100.
        // But they must be calculated before that via calculator
        put("paladin-weapon-battlepower", 60);   //only this was not right
        put("paladin-weapon-priceforupgrade", 50);

        put("paladin-offhand-battlepower", 50);
        put("paladin-offhand-priceforupgrade", 100);

        put("paladin-armor-battlepower", 20);
        put("paladin-armor-priceforupgrade", 75);


        put("mage-weapon-battlepower", 20); //was wrong
        put("mage-weapon-priceforupgrade", 67); //was wrong

        put("mage-offhand-battlepower", 20);
        put("mage-offhand-priceforupgrade", 100);

        put("mage-armor-battlepower", 100);
        put("mage-armor-priceforupgrade", 100);
    }};

    private void increaseBattlePower(Field battlePowerField, Item itemObject, String property) throws IllegalAccessException {
        Integer oldBattlePower = (Integer) battlePowerField.get(itemObject);
        Integer modification = this.itemTrainProperties.get(property);  //TODO modification is null!
        Integer newBattlePower = (oldBattlePower * (modification + 100)) / 100; //neshto * 160 / 100

        battlePowerField.set(itemObject, newBattlePower);
    }

    private void increasePriceForUpgrade(Field priceForUpgradeField, Item itemObject, String property) throws IllegalAccessException {
        Double oldPriceForUpgrade = (Double) priceForUpgradeField.get(itemObject);
        Integer modification = this.itemTrainProperties.get(property);
        Double newPriceForUpgrade = (oldPriceForUpgrade * (modification + 100)) / 100;

        priceForUpgradeField.set(itemObject, newPriceForUpgrade);
    }

    private String getItemType(String itemName) { //useless
        if (itemName.contains("weapon")) //fixed weapn
            return "weapon"; //TODO is "weapn" going to make problems?
        else if (itemName.contains("offhand"))
            return "offhand";
        else if (itemName.contains("armor"))
            return "armor";
        return null;
    }

    private void trainItemBattlePower(String itemType, Item itemObject, Hero hero) throws NoSuchFieldException, IllegalAccessException {
        Field battlePowerField = itemObject
                .getClass()
                .getSuperclass()
                .getDeclaredField("battlePower");

        battlePowerField.setAccessible(true);

        String propertyBattlePower = String.format("%s-%s-battlepower",
                hero.getHeroClass().toLowerCase().replace("hero", ""), itemType);
        //TODO problem! have to replace .getHeroClass().toLowerCase() with same, but replace "hero" with ""

        this.increaseBattlePower(battlePowerField, itemObject, propertyBattlePower);
        //                           the field     //the item   the "paladin-weapon-battlepower" key, which gives percentage
    }

    private void trainItemPriceForUpgrade(String itemType, Item itemObject, Hero hero) throws NoSuchFieldException, IllegalAccessException {
        Field priceForUpgradeField = itemObject
                .getClass()
                .getSuperclass()
                .getDeclaredField("priceForUpgrade");

        priceForUpgradeField.setAccessible(true);

        String propertyPriceForUpgrade = String.format("%s-%s-priceforupgrade",
                hero.getHeroClass().toLowerCase().replace("hero", ""), itemType);

        this.increasePriceForUpgrade(priceForUpgradeField, itemObject, propertyPriceForUpgrade);
    }

    private void trainItem(Item item, Hero hero) throws IllegalAccessException, NoSuchFieldException {
        String itemType = this.getItemType(item.getClass().getSimpleName().toLowerCase()); //kinda useless

        this.trainItemBattlePower(itemType, item, hero);
        this.trainItemPriceForUpgrade(itemType, item, hero);
    }

    @Override
    public void trainHero(Hero hero) { //there was a return at the beginning of the method (1st line)
        try {
            //hero has enough gold and has already payed for the training
            this.trainItem(hero.getWeapon(), hero);
            this.trainItem(hero.getOffhand(), hero);
            this.trainItem(hero.getArmor(), hero);

//            Method increaseTimesTrainedMethod = hero.getClass().getSuperclass().getDeclaredMethod("increaseTimesTrained", null);
//            increaseTimesTrainedMethod.setAccessible(true);
//            increaseTimesTrainedMethod.invoke(hero, null);
//            increaseTimesTrainedMethod.setAccessible(false);
        } catch (IllegalAccessException | NoSuchFieldException  e) {
            e.printStackTrace();
        }
    }
}