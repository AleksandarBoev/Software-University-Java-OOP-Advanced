package p02_extendedDatabase.models;

import p02_extendedDatabase.contracts.Person;

public class PersonImpl implements Person, Comparable<PersonImpl> {
    private long id;
    private String name;

    public PersonImpl(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }


    @Override
    public int compareTo(PersonImpl anotherPerson) {
        return 0;
    }

    @Override
    public boolean equals(Object anotherPerson) { //TODO there is probably a better way
        anotherPerson = (PersonImpl) anotherPerson;
        PersonImpl anotherPersonCasted = (PersonImpl) anotherPerson;
        return this.getId() == anotherPersonCasted.getId() && this.getName().equals(anotherPersonCasted.getName());
        //doing the comparison by getters, because of Mockito in the testing class
    }
}
