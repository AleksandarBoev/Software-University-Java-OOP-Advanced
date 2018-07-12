package demo.simpleGenericExample;

public class Main {
    public static void main(String[] args) {
        AnimalList animalList = new AnimalList();
        Dog dog = new Dog("German shepard", 5);
        Tiger tiger = new Tiger("White tiger", 18);

        animalList.add(dog);
        animalList.add(tiger);
    }
}
