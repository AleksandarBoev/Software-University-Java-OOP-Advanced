package hell.factories;

import hell.entities.items.RecipeItem;
import hell.interfaces.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeItemFactory implements Factory<Recipe> {
//    Recipe Spear Ivan 25 10 10 100 50 Knife Stick

    @Override
    public Recipe create(String[] data) {
        String name = data[1];
        int[] stats = Arrays.stream(data)
                .skip(3)
                .takeWhile(e -> this.isDigit(e))
                .mapToInt(x -> Integer.parseInt(x))
                .toArray();

        List<String> requiredItems = (ArrayList)Arrays.stream(data).skip(8).collect(Collectors.toList());

        return new RecipeItem(name, stats[0], stats[1], stats[2], stats[3], stats[4], requiredItems);
    }

    private boolean isDigit(String text) {
        return text.matches("[0-9]+");
    }
}
