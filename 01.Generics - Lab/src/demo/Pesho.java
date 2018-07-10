package demo;

public class Pesho{
    private int age;
    private String name;

    public Pesho(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("%s is the name, %d is the age!", this.name, this.age);
    }


}
