package panzer.abstract_classes;

import panzer.contracts.Part;

import java.math.BigDecimal;

public abstract class BasePart implements Part {
    //•	Model – a string.
    //•	Weight – a floating-point number.
    //•	Price – a decimal number.
    private String model;
    private double weight;
    private BigDecimal price;

    protected BasePart(String model, double weight, BigDecimal price) {
        this.model = model;
        this.weight = weight;
        this.price = price;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String toString() {
        //{partType} Part – {partModel}
        return String.format("%s Part - %s%n", this.getClass().getSimpleName(), this.model);
    }
}
