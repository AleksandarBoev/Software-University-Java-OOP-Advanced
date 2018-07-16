package p07_equalityLogic;

public class Person implements Comparable<Person> {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person anotherPerson) {
        int comparisonResult = this.name.compareTo(anotherPerson.name);
        if (comparisonResult != 0)
            return comparisonResult;

        return this.age.compareTo(anotherPerson.age);
    }

    @Override
    public int hashCode() { //TODO figure out what is happening here
        return (int) this.name.hashCode() * this.age.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)  //if both referant types point to same object
            return true;

        if (object == null)
            return false;

        if (this.getClass() != object.getClass()) // if they are different classes, they can't be the same
            return false;

        Person anotherPerson = (Person) object;
        return this.name.equals(anotherPerson.name) && this.age.compareTo(anotherPerson.age) == 0;
    }
}
