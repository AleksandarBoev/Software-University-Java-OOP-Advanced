package panzer.entities.factories;

import panzer.contracts.Factory;
import panzer.contracts.Part;
import panzer.entities.parts.ArsenalPart;
import panzer.entities.parts.EndurancePart;
import panzer.entities.parts.ShellPart;

import java.math.BigDecimal;
import java.util.List;

public class PartFactory implements Factory<Part> {
    @Override
    public Part create(List<String> arguments) {
        //private String model;
        //    private double weight;
        //    private BigDecimal price;


        //Part Destroyer-2U Arsenal Cannon-X 1000 50000 5000
        String partType = arguments.get(2);
        String model = arguments.get(3);
        double weight = Double.parseDouble(arguments.get(4));
//        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(arguments.get(5)));
        BigDecimal price = new BigDecimal(arguments.get(5));
        int additionalParam = Integer.parseInt(arguments.get(6));

        switch (partType) {
            case "Arsenal":
                return new ArsenalPart(model, weight, price, additionalParam);
            case "Endurance":
                return new EndurancePart(model, weight, price, additionalParam);
            case "Shell":
                return new ShellPart(model, weight, price, additionalParam);
            default:
                return null;
        }
    }
}
