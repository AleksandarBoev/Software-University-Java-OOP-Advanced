package hell.factories;

import hell.factories.CommonItemFactory;
import hell.factories.Factory;
import hell.factories.HeroFactory;
import hell.factories.RecipeItemFactory;
import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

public class TestingFactories {
    public static void main(String[] args) {
        Factory<Recipe> recipeFactory = new RecipeItemFactory();
        String[] testing = new String[]{"Recipe", "Spear", "Ivan", "25", "10", "10", "100", "50", "Knife", "Stick"};
        Recipe recipe = recipeFactory.create(testing);

        Factory<Item> commonItemFactory = new CommonItemFactory();
        Item commonItem = commonItemFactory.create(new String[] {"Item", "Stick", "Ivan", "0", "0", "10", "0", "5"});

        Factory<Hero> heroFactory = new HeroFactory();
        Hero pesho = heroFactory.create(new String[] {"Hero", "Pesho", "Assassin"});
    }
}
