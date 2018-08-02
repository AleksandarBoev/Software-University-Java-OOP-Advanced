package p02_extendedDatabase.models;

import p02_extendedDatabase.contracts.DatabaseExtendedPerson;
import p02_extendedDatabase.contracts.Person;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseExtendedPersonImpl implements DatabaseExtendedPerson {
    private static final int FIXED_ARRAY_SIZE = 16;
    private static final String ARRAY_LENGTH_IS_TOO_BIG_MESSAGE = String.format("Given collection is bigger than the allowed capacity of %d", FIXED_ARRAY_SIZE);
    private static final String REMOVING_ELEMENT_FROM_EMPTY_COLLECTION_MESSAGE = "Collection is empty, you can't remove elements from it!";
    private static final String PASSING_NULL_ELEMENT_MESSAGE = "You tried passing a null element.";
    private static final String MAXIMUM_CAPACITY_REACHED = "Maximum capacity of collection has been reached.";
    private static final String USER_ALREADY_EXISTS_MESSAGE = "A user with this data already exists!";
    private static final String PERSON_NOT_FOUND_EXCEPTION = "User with this data could not be found.";

    private List<Person> collection;

    public DatabaseExtendedPersonImpl(Person... varargs) throws OperationNotSupportedException {
        if (varargs.length > 16) {
            throw new OperationNotSupportedException(ARRAY_LENGTH_IS_TOO_BIG_MESSAGE);
        }
        this.collection = new ArrayList<>(16);

        for (int i = 0; i < varargs.length; i++) { //TODO again, problem with referent types
            this.add(varargs[i]);
        }
    }


    @Override
    public void add(Person person) throws OperationNotSupportedException {
        //o	If there are multiple users with this id, throw OperationNotSupportedException.
        //o	If negative nor null ids are present, throw OperationNotSupportedException.
        //o	If there are already 16 elements and add is called, throw OperationNotSupportedException.
        if (person == null) {
            throw new OperationNotSupportedException(PASSING_NULL_ELEMENT_MESSAGE);
        }

        if (this.collection.size() == FIXED_ARRAY_SIZE) {
            throw new OperationNotSupportedException(MAXIMUM_CAPACITY_REACHED);
        }

        if (this.userAlreadyExists(person.getId()) || this.userAlreadyExists(person.getName())) {
            throw new OperationNotSupportedException(USER_ALREADY_EXISTS_MESSAGE);
        }

        this.collection.add(person);
    }

    @Override
    public void remove() throws OperationNotSupportedException {
        //o	If no elements are present when remove is called, throw OperationNotSupportedException.
        if (this.collection.isEmpty()) {
            throw new OperationNotSupportedException(REMOVING_ELEMENT_FROM_EMPTY_COLLECTION_MESSAGE);
        }

        this.collection.remove(this.collection.size() - 1);
    }

    @Override
    public Person[] fetch() {
        Person[] result = (PersonImpl[])this.collection.toArray();
        return result;
    }

    @Override
    public Person findByUsername(String username) throws OperationNotSupportedException {
        //o	If no user is present by this username, throw OperationNotSupportedException.
        //o	If username parameter is null, throw OperationNotSupportedException.
        //o	Arguments are all CaseSensitive!
        if (username == null)
            throw new OperationNotSupportedException(PASSING_NULL_ELEMENT_MESSAGE);

        Person foundPerson = null;
        for (Person person : this.collection) {
            if (person.getName().equals(username)) {
                foundPerson = person;
                break;
            }
        }

        if (foundPerson == null)
            throw new OperationNotSupportedException(PERSON_NOT_FOUND_EXCEPTION);
        else
            return foundPerson;
    }

    @Override
    public Person findById(long id) throws OperationNotSupportedException {
        Person foundPerson = null;
        for (Person person : this.collection) {
            if (person.getId() == id) {
                foundPerson = person;
                break;
            }
        }

        if (foundPerson == null)
            throw new OperationNotSupportedException(PERSON_NOT_FOUND_EXCEPTION);
        else
            return foundPerson;
    }

    private boolean userAlreadyExists(String name) {
        for (Person person : this.collection) {
            if (person.getName().equals(name))
                return true;
        }

        return false;
    }

    private boolean userAlreadyExists(long id) {
        for (Person person : this.collection) {
            if (person.getId() == id)
                return true;
        }

        return false;
    }
}
