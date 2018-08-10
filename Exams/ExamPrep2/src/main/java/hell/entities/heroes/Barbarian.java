package hell.entities.heroes;

import hell.interfaces.Inventory;

public class Barbarian extends BaseHero {
    private static final int BASE_STRENGTH = 90;
    private static final int BASE_AGILITY = 25;
    private static final int BASE_INTELLIGENCE = 10;
    private static final int BASE_HIT_POINTS = 350;
    private static final int BASE_DAMAGE = 150;

    public Barbarian(String name, Inventory inventory) {
        super(name, BASE_STRENGTH, BASE_AGILITY, BASE_INTELLIGENCE, BASE_HIT_POINTS, BASE_DAMAGE, inventory);
    }
}
