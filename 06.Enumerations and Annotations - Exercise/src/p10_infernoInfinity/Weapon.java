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
    private Gem[] sockets;

    Weapon(int baseMinDamage, int baseMaxDamage, int numberOfSockets) {
        this.baseMinDamage = baseMinDamage;
        this.baseMaxDamage = baseMaxDamage;
        this.sockets = new Gem[numberOfSockets];
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addGem(int socketIndex, Gem gem) {
        try {
            this.sockets[socketIndex] = gem;
        } catch (IndexOutOfBoundsException ioobe) {

        }
    }

    public void removeGem(int socketIndex) {
        try {
            this.sockets[socketIndex] = null;
        } catch (IndexOutOfBoundsException ioobe) {

        }
    }

    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        this.activateSockets();
        return String.format("%s: %d-%d Damage, +%d Strength, +%d Agility, +%d Vitality",
                this.name, this.minDamage, this.maxDamage,
                this.getAllStrengthBonuses(), this.getAllAgilityBonuses(), this.getAllVitalityBonuses());
    }

    private void activateSockets() { //updates min and max damage
        int minDamage = this.baseMinDamage;
        int maxDamage = this.baseMaxDamage;

        minDamage += this.getAllStrengthBonuses() * STRENGTH_BONUS_TO_MIN_DAMAGE
                +this.getAllAgilityBonuses() * AGILITY_BONUS_TO_MIN_DAMAGE
                +this.getAllVitalityBonuses() * VITALITY_BONUS_TO_MIN_DAMAGE;

        maxDamage += this.getAllStrengthBonuses() * STRENGTH_BONUS_TO_MAX_DAMAGE
                +this.getAllAgilityBonuses() * AGILITY_BONUS_TO_MAX_DAMAGE
                +this.getAllVitalityBonuses() * VITALITY_BONUS_TO_MAX_DAMAGE;

        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    private int getAllStrengthBonuses() {
        int result = 0;
        for (int socketIndex = 0; socketIndex < this.sockets.length; socketIndex++) {
            if (this.sockets[socketIndex] == null) {
                continue;
            } else {
                result += this.sockets[socketIndex].getStrength();
            }
        }

        return result;
    }

    private int getAllAgilityBonuses() {
        int result = 0;
        for (int socketIndex = 0; socketIndex < this.sockets.length; socketIndex++) {
            if (this.sockets[socketIndex] == null) {
                continue;
            } else {
                result += this.sockets[socketIndex].getAgility();
            }
        }

        return result;
    }

    private int getAllVitalityBonuses() {
        int result = 0;
        for (int socketIndex = 0; socketIndex < this.sockets.length; socketIndex++) {
            if (this.sockets[socketIndex] == null) {
                continue;
            } else {
                result += this.sockets[socketIndex].getVitality();
            }
        }

        return result;
    }


}
