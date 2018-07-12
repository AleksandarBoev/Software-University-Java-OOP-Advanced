package demo.simpleGenericExample;

public class Tiger extends Animal {
    public Tiger(String species, int dangerLevel) {
        super(species, dangerLevel);
    }

    @Override
    public void makeSound() {
        System.out.println("Roar");
    }

    @Override
    public String toString() {
        return "I am a Tiger, roar";
    }
}
