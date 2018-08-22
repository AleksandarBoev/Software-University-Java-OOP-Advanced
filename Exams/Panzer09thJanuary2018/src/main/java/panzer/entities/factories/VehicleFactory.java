package panzer.entities.factories;

import panzer.contracts.Factory;
import panzer.contracts.Vehicle;
import panzer.entities.vehicles.Revenger;
import panzer.entities.vehicles.Vanguard;

import java.math.BigDecimal;
import java.util.List;

public class VehicleFactory implements Factory<Vehicle> {
    @Override
    public Vehicle create(List<String> arguments) {
        String vehicleType = arguments.get(1);

        //private String model;
        //    private double weight;
        //    private BigDecimal price;
        //    private int attack;
        //    private int defence;
        //    private int hitPoints;

        //Vehicle Revenger Destroyer-2U 1500 100000 9500 5000 15000
        String model = arguments.get(2);
        double weight = Double.parseDouble(arguments.get(3));
        BigDecimal price = new BigDecimal(arguments.get(4));
        int attack = Integer.parseInt(arguments.get(5));
        int defence = Integer.parseInt(arguments.get(6));
        int hitPoints = Integer.parseInt(arguments.get(7));

        switch (vehicleType) {
            case "Revenger":
                return new Revenger(model, weight, price, attack, defence, hitPoints);
            case "Vanguard":
                return new Vanguard(model, weight, price, attack, defence, hitPoints);
            default:
                return null;
        }
    }
}
