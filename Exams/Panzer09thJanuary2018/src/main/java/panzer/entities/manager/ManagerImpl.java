package panzer.entities.manager;

import panzer.contracts.*;
import panzer.core.PanzerBattleOperator;
import panzer.entities.factories.PartFactory;
import panzer.entities.factories.VehicleFactory;
import panzer.entities.parts.ArsenalPart;
import panzer.entities.parts.EndurancePart;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ManagerImpl implements Manager {
    private Map<String, Vehicle> vehicles;
    private Map<String, Vehicle> defeatedVehicles;
    private Map<String, Part> parts;
    private BattleOperator battleOperator;

    private Factory<Vehicle> vehicleFactory; //it would be better for the factories to be in the engine, not here
    private Factory<Part> partFactory; //but then I would have to change certain code, which would break judge

    public ManagerImpl() {
        this.vehicles = new LinkedHashMap<>();
        this.defeatedVehicles = new LinkedHashMap<>();
        this.vehicleFactory = new VehicleFactory();
        this.partFactory = new PartFactory();
        this.parts = new LinkedHashMap<>();
        this.battleOperator = new PanzerBattleOperator();
    }

    @Override
    public String addVehicle(List<String> arguments) {
        Vehicle newVehicle = this.vehicleFactory.create(arguments);
        this.vehicles.put(newVehicle.getModel(), newVehicle);
        return String.format("Created %s Vehicle - %s", arguments.get(1), arguments.get(2));
    }

    @Override
    public String addPart(List<String> arguments) {
        //Part SA-203 Arsenal Cannon-KA2 300 500 450
        Part newPart = this.partFactory.create(arguments);
        Vehicle vehicle = this.vehicles.get(arguments.get(1));

        if (newPart instanceof ArsenalPart) { //bad practise
            vehicle.addArsenalPart(newPart);
        } else if (newPart instanceof EndurancePart) {
            vehicle.addEndurancePart(newPart);
        } else {
            vehicle.addShellPart(newPart);
        }
        //Added Arsenal - Cannon-KA2 to Vehicle - SA-203
        return String.format("Added %s - %s to Vehicle - %s", arguments.get(2), arguments.get(3), arguments.get(1));
    }

    @Override
    public String inspect(List<String> arguments) {
        String model = arguments.get(1);
        if (this.vehicles.containsKey(model)) {
            return this.vehicles.get(model).toString();
        } else if (this.defeatedVehicles.containsKey(model)) {
            return this.defeatedVehicles.get(model).toString();
        } else
            return this.parts.get(model).toString();
    }

    @Override
    public String battle(List<String> arguments) {
        //•	Battle {vehicle1Model} {vehicle2Model}
        Vehicle vehicle1 = this.vehicles.get(arguments.get(1));
        Vehicle vehicle2 = this.vehicles.get(arguments.get(2));

        String vehicleWinnerModel = this.battleOperator.battle(vehicle1, vehicle2);
        if (vehicle1.getModel().equals(vehicleWinnerModel)) {
            this.vehicles.remove(vehicle2.getModel());
            this.defeatedVehicles.put(vehicle2.getModel(), vehicle2);
        } else {
            this.vehicles.remove(vehicle1);
            this.defeatedVehicles.put(vehicle1.getModel(), vehicle1);
        }

        return String.format("%s versus %s -> %s Wins! Flawless Victory!",
                arguments.get(1), arguments.get(2), vehicleWinnerModel);
    }

    @Override
    public String terminate(List<String> arguments) {
        String remainingVehicles = String.join(", ", this.vehicles.keySet());
        if ("".equals(remainingVehicles)) {
            remainingVehicles = "None";
        }

        String defeatedVehicles = String.join(", ", this.defeatedVehicles.keySet());
        if ("".equals(defeatedVehicles)) {
            defeatedVehicles = "None";
        }

        int currentlyUsedParts = 0;
        for (Vehicle vehicle : vehicles.values()) {
            for (Part part : vehicle.getParts()) {
                currentlyUsedParts++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Remaining Vehicles: ").append(remainingVehicles).append(System.lineSeparator());
        sb.append("Defeated Vehicles: ").append(defeatedVehicles).append(System.lineSeparator());
        sb.append("Currently Used Parts: ").append(currentlyUsedParts);

        return sb.toString();
    }
}
