package p05_comparingObjects;

public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private String town;

    public Person(String name, int age, String town) {
        this.name = name;
        this.age = age;
        this.town = town;
    }

    @Override
    public int compareTo(Person anotherPerson) {
        int comparisonResult = this.name.compareTo(anotherPerson.name);
        if (comparisonResult != 0)
            return comparisonResult;

        comparisonResult = Integer.compare(this.age, anotherPerson.age);
        if (comparisonResult != 0)
            return comparisonResult;

        return this.town.compareTo(anotherPerson.town);
    }
}
