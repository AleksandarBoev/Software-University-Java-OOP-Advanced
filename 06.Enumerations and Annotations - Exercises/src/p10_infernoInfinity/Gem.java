package p10_infernoInfinity;

public enum Gem {
    //•	Ruby (+7 strength, +2 agility, +5 vitality)
    //•	Emerald (+1strength, +4 agility, +9 vitality)
    //•	Amethyst (+2 strength, +8 agility, +4 vitality)
    RUBY(7, 2, 5), EMERALD(1, 4, 9), AMETHYST(2, 8, 4);

    private int strength;
    private int agility;
    private int vitality;

    Gem(int strength, int agility, int vitality) {
        this.strength = strength;
        this.agility = agility;
        this.vitality = vitality;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getAgility() {
        return this.agility;
    }

    public int getVitality() {
        return this.vitality;
    }
}
