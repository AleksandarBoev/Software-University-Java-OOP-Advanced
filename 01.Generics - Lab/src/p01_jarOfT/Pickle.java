package p01_jarOfT;

public class Pickle {
    private int someField;

    public Pickle(int someField) {
        this.someField = someField;
    }

    @Override
    public String toString() {
        return String.format("Some field value: %d", this.someField);
    }
}
