package hell.factories;

import hell.entities.items.CommonItem;
import hell.interfaces.Item;

import java.util.Arrays;

public class CommonItemFactory implements Factory<Item> {
    //Item Knife Ivan 0 10 0 0 30
    @Override
    public Item create(String[] data) {
        String itemName = data[1];
        int[] stats = Arrays.stream(data).skip(3).mapToInt(x -> Integer.parseInt(x)).toArray();
        return new CommonItem(itemName, stats[0], stats[1], stats[2], stats[3], stats[4]);
    }
}
