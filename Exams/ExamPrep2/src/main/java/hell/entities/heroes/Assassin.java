package hell.entities.heroes;

import hell.interfaces.Inventory;

public class Assassin extends BaseHero {
    private static final int BASE_STRENGTH = 25;
    private static final int BASE_AGILITY = 100;
    private static final int BASE_INTELLIGENCE = 15;
    private static final int BASE_HIT_POINTS = 150;
    private static final int BASE_DAMAGE = 300;

    public Assassin(String name, Inventory inventory) {
        super(name, BASE_STRENGTH, BASE_AGILITY, BASE_INTELLIGENCE, BASE_HIT_POINTS, BASE_DAMAGE, inventory);
    }
}
