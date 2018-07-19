package demo.enums;

public enum MyEnum {
    MERCEDES, AUDI, TRABANT(100.0, "Pesho");
    //other scenario of this enum: not having an empty constructor, the mini classes above
    //would need two parameters like "TRABANT", otherwise there would be a Compile Time Error
    //also if I don't add setters, then these mini classes would look more like constants (with initial hardcoded values because of no empty Constructor)

    private double price;
    private String owner;

    MyEnum() {

    }

    MyEnum(double price, String owner) { //if there wasn't an empty constructor, MERCEDES (and the others) would have wanted two parameters.
        this.price = price;
        this.owner = owner;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String randomMethod() {
        String normalName = this.name().charAt(0) + this.name().substring(1).toLowerCase();
        return String.format("The car is: %s", normalName);
    }
}
