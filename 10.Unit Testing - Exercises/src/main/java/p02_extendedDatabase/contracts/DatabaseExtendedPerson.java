package p02_extendedDatabase.contracts;

import p02_extendedDatabase.models.PersonImpl;

import javax.naming.OperationNotSupportedException;

public interface DatabaseExtendedPerson extends DatabaseExtended<Person> {

    Person findByUsername(String username) throws OperationNotSupportedException;

    Person findById(long id) throws OperationNotSupportedException;
}
