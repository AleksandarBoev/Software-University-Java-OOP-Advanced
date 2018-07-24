package p10_infernoInfinity;

public enum Weapon {
    AXE(5, 10, 4), SWORD(4, 6, 3), KNIFE(3, 4, 2);

    //Every point of strength adds +2 to min damage and +3 to max damage.
    // Every point of agility adds +1 to min damage and +4 to max damage.
    // Vitality does not add damage.
    private static final int STRENGTH_BONUS_TO_MIN_DAMAGE = 2;
    private static final int STRENGTH_BONUS_TO_MAX_DAMAGE = 3;
    private static final int AGILITY_BONUS_TO_MIN_DAMAGE = 1;
    private static final int AGILITY_BONUS_TO_MAX_DAMAGE = 4;
    private static final int VITALITY_BONUS_TO_MIN_DAMAGE = 0;
    private static final int VITALITY_BONUS_TO_MAX_DAMAGE = 0;

    private String name; //not sure this is good practise
    private int baseMinDamage;
    private int baseMaxDamage;
    private int minDamage;
    private int maxDamage;
    private int strength;
    private int agility;
    private int vitality;
    private Gem[] sockets;

    Weapon(int baseMinDamage, int baseMaxDamage, int numberOfSockets) {
        this.baseMinDamage = baseMinDamage;
        this.baseMaxDamage = baseMaxDamage;
        this.sockets = new Gem[numberOfSockets];
        this.strength = 0;
        this.agility = 0;
        this.vitality = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addGem(int socketIndex, Gem gem) {
        try {
            if (this.sockets[socketIndex] != null) { //if there is already a gem in this socket, get rid of it
                this.removeGem(socketIndex);
            }
            this.sockets[socketIndex] = gem;
            this.strength += gem.getStrength();
            this.agility += gem.getAgility();
            this.vitality += gem.getVitality();
        } catch (IndexOutOfBoundsException ioobe) {

        }
    }

    public void removeGem(int socketIndex) {
        try {
            Gem gem = this.sockets[socketIndex];
            this.strength -= gem.getStrength(); //if gem is null --> nullpointer exception
            this.agility -= gem.getAgility();
            this.vitality -= gem.getVitality();
            gem = null;
        } catch (IndexOutOfBoundsException | NullPointerException ioobeNpe) {

        }
    }

    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        this.updateDamage();
        return String.format("%s: %d-%d Damage, +%d Strength, +%d Agility, +%d Vitality",
                this.name, this.minDamage, this.maxDamage,
                this.strength, this.agility, this.vitality);
    }

    private void updateDamage() {
        int minDamage = this.baseMinDamage;
        int maxDamage = this.baseMaxDamage;

        minDamage += this.strength * STRENGTH_BONUS_TO_MIN_DAMAGE
                +this.agility * AGILITY_BONUS_TO_MIN_DAMAGE
                +this.vitality * VITALITY_BONUS_TO_MIN_DAMAGE;

        maxDamage += this.strength * STRENGTH_BONUS_TO_MAX_DAMAGE
                +this.agility * AGILITY_BONUS_TO_MAX_DAMAGE
                +this.vitality * VITALITY_BONUS_TO_MAX_DAMAGE;

        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }


}
