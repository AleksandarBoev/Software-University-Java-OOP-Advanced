package demo.constructors;

public class Person {
    private String name;
    private int age;

    public Person() {
        this.name = "No name";
        this.age = -1;
    }

    public Person(String name) {
        this.name = name;
        this.age = -1;
    }

    public Person(int age) {
        this.name = "No name";
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
