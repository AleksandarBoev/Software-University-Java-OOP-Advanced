package demo.simpleGenericExample;

public abstract class Animal {
    private String species;
    private int dangerLevel;

    public Animal(String species, int dangerLevel) {
        this.species = species;
        this.dangerLevel = dangerLevel;
    }

    public abstract void makeSound();
}
