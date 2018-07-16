package p06_strategyPattern;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getFirstLetter() {
        return "" + this.name.charAt(0);
    }

    public int getNameLength() {
        return this.name.length();
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return String.format("%s %d", this.name, this.age);
    }
}
