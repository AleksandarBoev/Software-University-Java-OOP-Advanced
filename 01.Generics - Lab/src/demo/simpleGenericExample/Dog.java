package demo.simpleGenericExample;

public class Dog extends Animal {
    public Dog(String species, int dangerLevel) {
        super(species, dangerLevel);
    }

    @Override
    public void makeSound() {
        System.out.println("Baouau");
    }

    @Override
    public String toString() {
        return "Dog here";
    }
}
