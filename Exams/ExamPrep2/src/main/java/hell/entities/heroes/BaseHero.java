package hell.entities.heroes;

import hell.entities.miscellaneous.HeroInventory;
import hell.entities.miscellaneous.ItemCollection;
import hell.interfaces.Hero;
import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseHero implements Hero {
    //•	Name – a string, indicating the name of the hero.
    //•	Strength – an integer, indicating the strength of the hero.
    //•	Agility – an integer, indicating the agility of the hero.
    //•	Intelligence – an integer, indicating the intelligence of the hero.
    //•	HitPoints – an integer, indicating the hit points of the hero.
    //•	Damage – an integer, indicating the damage of the hero.
    private String name;
    private int strength;
    private int agility;
    private int intelligence;
    private int hitPoints;
    private int damage;
    private Inventory inventory;

    protected BaseHero(String name, int strength, int agility, int intelligence, int hitPoints, int damage, Inventory inventory) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.inventory = inventory;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getStrength() {
        return (long)this.strength + this.inventory.getTotalStrengthBonus();
    }

    @Override
    public long getAgility() {
        return (long)this.agility + this.inventory.getTotalAgilityBonus();
    }

    @Override
    public long getIntelligence() {
        return (long)this.intelligence + this.inventory.getTotalIntelligenceBonus();
    }

    @Override
    public long getHitPoints() {
        return (long)this.hitPoints + this.inventory.getTotalHitPointsBonus();
    }



    @Override
    public long getDamage() {
        return (long)this.damage + this.inventory.getTotalDamageBonus();
    }

//    @SuppressWarnings("unchecked")
//    @Override
//    public Collection<Item> getItems() throws NoSuchFieldException, IllegalAccessException {
//        //    private Map<String, Item> commonItems;
//        Field commonItemsField = this.inventory.getClass().getDeclaredField("commonItems");
//        commonItemsField.setAccessible(true);
//        //using reflection, because task does not permit creating additional public methods, which includes getters
//        Map<String, Item> extractedField = (Map<String, Item>)commonItemsField.get(this.inventory);
//        Collection<Item> result = extractedField.values();
//
//        commonItemsField.setAccessible(false);
//
//        return result;
//    }



    @Override

    @SuppressWarnings("unchecked")

    public Collection<Item> getItems() {
        Collection<Item> items = null;
        Field[] inventoryFields = this.inventory.getClass().getDeclaredFields();

        for (Field inventoryField : inventoryFields) {
            if (inventoryField.isAnnotationPresent(ItemCollection.class)) {
                inventoryField.setAccessible(true);
                Map<String, Item> itemMap = null;

                try {
                    itemMap = (Map<String, Item>) inventoryField.get(this.inventory);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                items = itemMap.values();
            }
        }
        return items;
    }

    @Override
    public void addItem(Item item) {
        this.inventory.addCommonItem(item);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        this.inventory.addRecipeItem(recipe);
    }

    @Override
    public String toString() { //TODO do this
        //Hero: Ivan, Class: Barbarian //this is going to be toString method
        //HitPoints: 450, Damage: 200
        //Strength: 115
        //Agility: 35
        //Intelligence: 20
        //Items:
        //###Item: Spear
        //###+25 Strength
        //###+10 Agility
        //###+10 Intelligence
        //###+100 HitPoints
        //###+50 Damage

        StringBuilder sb = new StringBuilder();
        sb.append("Hero: ").append(this.name).append(", Class: ").append(this.getClass().getSimpleName()).append(System.lineSeparator());
        sb.append("HitPoints: ").append(this.getHitPoints()).append(", Damage: ").append(this.getDamage()).append(System.lineSeparator());
        sb.append("Strength: ").append(this.getStrength()).append(System.lineSeparator());
        sb.append("Agility: ").append(this.getAgility()).append(System.lineSeparator());
        sb.append("Intelligence: ").append(this.getIntelligence()).append(System.lineSeparator());
        sb.append("Items: ");

        Field commonItemsField = null; //maybe make a private method, which returns the extracted field?
        try {
            commonItemsField = this.inventory.getClass().getDeclaredField("commonItems");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        commonItemsField.setAccessible(true);

        Map<String, Item> extractedField = null;
        try {
            extractedField = (LinkedHashMap)commonItemsField.get(this.inventory);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        commonItemsField.setAccessible(false);

        if (extractedField.isEmpty()) {
            sb.append("None");
            return sb.toString();
        }

        sb.append(System.lineSeparator());
        sb.append(this.inventory.toString());
        return sb.toString();
    }
}
