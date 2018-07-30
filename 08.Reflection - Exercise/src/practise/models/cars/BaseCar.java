package practise.models.cars;

import practise.contracts.Car;

public abstract class BaseCar implements Car {
    private String model;
    private String ownerName;
    private double totalDistanceTraveled;
    private double gas;

    public BaseCar(String model, String ownerName) {
        this.model = model;
        this.ownerName = ownerName;
        this.totalDistanceTraveled = 0.0;
        this.gas = 0.0;
    }
}
