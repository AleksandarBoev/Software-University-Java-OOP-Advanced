package p13_createCustomClassAnnotations;

public enum WeaponType {
    AXE(5, 10, 4), SWORD(4, 6, 3), KNIFE(3, 4, 2);
    // Difference between this version of the task and the other one:
    // These values will NOT be changed. Only accessed. Using enums as they are supposed to be used: as constants.
    private int minDamage;
    private int maxDamage;
    private int gemCount;

    WeaponType(int minDamage, int maxDamage, int gemCount) {
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.gemCount = gemCount;
    }

    public int getMinDamage() {
        return this.minDamage;
    }

    public int getMaxDamage() {
        return this.maxDamage;
    }

    public int getGemCount() {
        return this.gemCount;
    }
}
