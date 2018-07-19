package demo.enums;

public enum Season {
    SPRING, SUMMER, FALL, WINTER;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
