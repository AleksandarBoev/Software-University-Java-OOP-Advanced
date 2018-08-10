package hell.interfaces;

import hell.entities.miscellaneous.HeroInventory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class InventoryTest {
    private static final int INT_MAX_VALUE = Integer.MAX_VALUE;
    private static final String ITEM_NAME = "Item %s";
    private static final String RECIPE_NAME = "Recipe %s";
    private static final String VALUE_MISSMATCH = "Expected %1$s and actual %1$s are not equal!";
    private static final String NULL_FIELD = "%s is null!";
    private static final String FINAL_INGREDIENT_NAME = "Item 4";
    private static final String NON_EXISTING_INGREDIENT = "Short Longsword";

    private Inventory inventory;
    private Item[] commonItems;

    private Map<String, Item> commonItemsExtracted;

    private Map<String, Recipe> recipeItemsExtracted;

    @Before
    public void init() throws NoSuchFieldException, IllegalAccessException {
        this.inventory = new HeroInventory();
        this.commonItems = new Item[3];

        for (int i = 0; i < 3; i++) {
            Item commonItem = Mockito.mock(Item.class);

            Mockito.when(commonItem.getName()).thenReturn(String.format(ITEM_NAME, i)); //this is needed, because Inventory uses Map<Name, Item>
            Mockito.when(commonItem.getDamageBonus()).thenReturn(INT_MAX_VALUE);
            Mockito.when(commonItem.getAgilityBonus()).thenReturn(INT_MAX_VALUE);
            Mockito.when(commonItem.getIntelligenceBonus()).thenReturn(INT_MAX_VALUE);
            Mockito.when(commonItem.getHitPointsBonus()).thenReturn(INT_MAX_VALUE);
            Mockito.when(commonItem.getStrengthBonus()).thenReturn(INT_MAX_VALUE);

            this.inventory.addCommonItem(commonItem);
            this.commonItems[i] = commonItem;
        }

        Field commonItemsField = this.inventory.getClass().getDeclaredField("commonItems");
        Field recipeItemsField = this.inventory.getClass().getDeclaredField("recipeItems");

        commonItemsField.setAccessible(true);
        recipeItemsField.setAccessible(true);

        this.commonItemsExtracted = (LinkedHashMap<String, Item>)commonItemsField.get(this.inventory);
        this.recipeItemsExtracted = (LinkedHashMap<String, Recipe>)recipeItemsField.get(this.inventory);

        recipeItemsField.setAccessible(false);
        commonItemsField.setAccessible(false);
    }

    @Test
    public void testGetTotalStrengthBonusIntOverflow() {
        long expectedResult = 3L * INT_MAX_VALUE;
        long actualValue = this.inventory.getTotalStrengthBonus();

        Assert.assertEquals(String.format(VALUE_MISSMATCH, "strength"), expectedResult, actualValue);
    }

    @Test
    public void testGetTotalAgilityBonusIntOverflow() {
        long expectedResult = 3L * INT_MAX_VALUE;
        long actualValue = this.inventory.getTotalAgilityBonus();

        Assert.assertEquals(String.format(VALUE_MISSMATCH, "agility"), expectedResult, actualValue);
    }

    @Test
    public void testGetTotalIntelligenceBonusIntOverflow() {
        long expectedResult = 3L * INT_MAX_VALUE;
        long actualValue = this.inventory.getTotalIntelligenceBonus();

        Assert.assertEquals(String.format(VALUE_MISSMATCH, "intelligence"), expectedResult, actualValue);
    }

    @Test
    public void testGetTotalHitPointsBonusIntOverflow() {
        long expectedResult = 3L * INT_MAX_VALUE;
        long actualValue = this.inventory.getTotalHitPointsBonus();

        Assert.assertEquals(String.format(VALUE_MISSMATCH, "hit points"), expectedResult, actualValue);
    }

    @Test
    public void testGetTotalDamageBonusIntOverflow() {
        long expectedResult = 3L * INT_MAX_VALUE;
        long actualValue = this.inventory.getTotalDamageBonus();

        Assert.assertEquals(String.format(VALUE_MISSMATCH, "damage"), expectedResult, actualValue);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAddCommonItemSizeAndElements() throws NoSuchFieldException, IllegalAccessException {

        Assert.assertEquals(String.format(VALUE_MISSMATCH, "size"), 3, this.commonItemsExtracted.size());

        int counter = 0;
        for (Item item : this.commonItemsExtracted.values()) {
            Assert.assertEquals(String.format(VALUE_MISSMATCH, "item"), this.commonItems[counter++], item);
            //uses the Object equals method, which checks their address in the memory. If their addresses in the memory are equal
            //then they are 100% the same!
        }
    }


    @Test
    @SuppressWarnings("unchecked")
    public void testAddRecipeItemSizeAndElements() throws IllegalAccessException, NoSuchFieldException {
        Recipe[] recipeItems = new Recipe[3];

        for (int i = 0; i < 3; i++) {
            Recipe currentRecipe = Mockito.mock(Recipe.class);
            Mockito.when(currentRecipe.getName()).thenReturn(String.format(RECIPE_NAME, i));

            List<String> requiredItems = new ArrayList<>(Arrays.asList(NON_EXISTING_INGREDIENT));
            Mockito.when(currentRecipe.getRequiredItems()).thenReturn(requiredItems);
            //^ needs to be done or the list will be empty and the moment a recipe is added it has all the ingredients and transforms into an Item
            recipeItems[i] = currentRecipe;
            this.inventory.addRecipeItem(currentRecipe);
        }

        Assert.assertEquals(String.format(VALUE_MISSMATCH, "size"), 3, this.recipeItemsExtracted.size());

        int counter = 0;
        for (Recipe recipe : this.recipeItemsExtracted.values()) {
            Assert.assertEquals(String.format(VALUE_MISSMATCH, "recipe"), recipeItems[counter++], recipe);
        }
    }

    @Test
    public void testConstructor() throws NoSuchFieldException, IllegalAccessException { //this test did not give any points
        Assert.assertTrue(String.format(NULL_FIELD, "Common items field"), this.commonItemsExtracted != null);
        Assert.assertTrue(String.format(NULL_FIELD, "Recipe items field"), this.recipeItemsExtracted != null);
    }

    @Test
    public void addingFinalIngredientToRecipeCreatesANewCommonItem() throws NoSuchFieldException, IllegalAccessException {
        Recipe mockedRecipe = Mockito.mock(Recipe.class);
        List<String> requiredItems = new ArrayList<>(this.commonItemsExtracted.keySet()); // adding all the item names
        requiredItems.add(FINAL_INGREDIENT_NAME);

        Mockito.when(mockedRecipe.getRequiredItems()).thenReturn(requiredItems); // required items are: "Item 1" ... "Item 4"
        String recipeName = String.format(RECIPE_NAME, 1);
        Mockito.when(mockedRecipe.getName()).thenReturn(recipeName);

        Item mockedCommonItem = Mockito.mock(Item.class);
        Mockito.when(mockedCommonItem.getName()).thenReturn(FINAL_INGREDIENT_NAME);

        this.inventory.addRecipeItem(mockedRecipe);
        this.inventory.addCommonItem(mockedCommonItem);

        //all 4 items should disappear and one item should be added - the completed recipe
        Assert.assertEquals(String.format(VALUE_MISSMATCH, "size"), 1, this.commonItemsExtracted.size());
        Assert.assertTrue(this.commonItemsExtracted.containsKey(recipeName));
//        Assert.assertTrue(this.recipeItemsExtracted.isEmpty()); //apparently, the recipe should NOT be removed after it is complete...
    }

    @Test
    public void addingARecipeWithAlreadyGatheredIngredientsCreatesNewCommonItem() {
        //same code, but first we add the last ingredient and THEN we add the recipe
        Recipe mockedRecipe = Mockito.mock(Recipe.class);
        List<String> requiredItems = new ArrayList<>(this.commonItemsExtracted.keySet()); // adding all the item names
        requiredItems.add(FINAL_INGREDIENT_NAME);

        Mockito.when(mockedRecipe.getRequiredItems()).thenReturn(requiredItems); // required items are: "Item 1" ... "Item 4"
        String recipeName = String.format(RECIPE_NAME, 1);
        Mockito.when(mockedRecipe.getName()).thenReturn(recipeName);

        Item mockedCommonItem = Mockito.mock(Item.class);
        Mockito.when(mockedCommonItem.getName()).thenReturn(FINAL_INGREDIENT_NAME);

        this.inventory.addCommonItem(mockedCommonItem);
        this.inventory.addRecipeItem(mockedRecipe);

        //all 4 items should disappear and one item should be added - the completed recipe
        Assert.assertEquals(String.format(VALUE_MISSMATCH, "size"), 1, this.commonItemsExtracted.size());
        Assert.assertTrue(this.commonItemsExtracted.containsKey(recipeName));
//        Assert.assertTrue(this.recipeItemsExtracted.isEmpty());
    }
}
