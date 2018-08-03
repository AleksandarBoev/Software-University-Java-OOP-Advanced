package comandPattern;

public class Car {
    private int year;
    private double kilometersDriven;

    public void drive(double distance) {
        this.kilometersDriven += distance;
    }

    public double calculateCondition() {
        return this.year * this.kilometersDriven / 1000.0;
    }
}
