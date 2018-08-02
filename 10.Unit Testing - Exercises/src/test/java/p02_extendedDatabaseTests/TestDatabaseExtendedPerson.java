package p02_extendedDatabaseTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import p02_extendedDatabase.contracts.DatabaseExtended;
import p02_extendedDatabase.contracts.DatabaseExtendedPerson;
import p02_extendedDatabase.contracts.Person;
import p02_extendedDatabase.models.DatabaseExtendedPersonImpl;
import p02_extendedDatabase.models.PersonImpl;

import javax.naming.OperationNotSupportedException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TestDatabaseExtendedPerson {
    private static final String VALUE_MISMATCH = "Values are NOT equal!";
    private static final String NAME_MISMATCH = "Names are not equal at %d";
    private static final String ID_MISMATCH = "IDs are not equal at %d";
    private static final String PEOPLE_ARE_DIFFERENT_MESSAGE = "People are different.";

    private static final String MOCKED_PERSON1_NAME = "Person1";
    private static final String MOCKED_PERSON2_NAME = "Person2";
    private static final String MOCKED_PERSON3_NAME = "Person3";
    private static final long MOCKED_PERSON1_ID = 1;
    private static final long MOCKED_PERSON2_ID = 2;
    private static final long MOCKED_PERSON3_ID = 3;
    private static final String ADDITIONAL_MOCKED_PERSON_NAME = "Person4";
    private static final long ADDITIONAL_MOCKED_PERSON_ID = 4;

    private DatabaseExtendedPerson databaseExtended;
    private Person[] mockedPeople;
    private Person additionalMockedPerson;
    List<Person> extractedCollection;

    @Before
    public void init() throws OperationNotSupportedException, NoSuchFieldException, IllegalAccessException {
        PersonImpl mockedPerson1 = Mockito.mock(PersonImpl.class);
        PersonImpl mockedPerson2 = Mockito.mock(PersonImpl.class);
        PersonImpl mockedPerson3 = Mockito.mock(PersonImpl.class);

        Mockito.when(mockedPerson1.getName()).thenReturn(MOCKED_PERSON1_NAME);
        Mockito.when(mockedPerson2.getName()).thenReturn(MOCKED_PERSON2_NAME);
        Mockito.when(mockedPerson3.getName()).thenReturn(MOCKED_PERSON3_NAME);

        Mockito.when(mockedPerson1.getId()).thenReturn(MOCKED_PERSON1_ID);
        Mockito.when(mockedPerson2.getId()).thenReturn(MOCKED_PERSON2_ID);
        Mockito.when(mockedPerson3.getId()).thenReturn(MOCKED_PERSON3_ID);

        this.mockedPeople = new PersonImpl[]{mockedPerson1, mockedPerson2, mockedPerson3}; //problem if declared via interface

        this.databaseExtended = new DatabaseExtendedPersonImpl(mockedPeople);

        this.additionalMockedPerson = Mockito.mock(PersonImpl.class);
        Mockito.when(this.additionalMockedPerson.getName()).thenReturn(ADDITIONAL_MOCKED_PERSON_NAME);
        Mockito.when(this.additionalMockedPerson.getId()).thenReturn(ADDITIONAL_MOCKED_PERSON_ID);

        Field collectionField = this.databaseExtended.getClass().getDeclaredField("collection");
        collectionField.setAccessible(true);
        this.extractedCollection = (ArrayList<Person>) collectionField.get(this.databaseExtended);
        collectionField.setAccessible(false);
    }

    @Test
    public void testConstructor() throws NoSuchFieldException, IllegalAccessException {
        Field collectionField = this.databaseExtended.getClass().getDeclaredField("collection");
        collectionField.setAccessible(true);
        List<Person> extractedCollection = (ArrayList<Person>) collectionField.get(this.databaseExtended);
        collectionField.setAccessible(false);

        Assert.assertEquals(VALUE_MISMATCH, this.mockedPeople.length, extractedCollection.size());

        for (int i = 0; i < extractedCollection.size(); i++) {
            Assert.assertEquals(String.format(NAME_MISMATCH, i), this.mockedPeople[i].getName(), extractedCollection.get(i).getName());
            Assert.assertEquals(String.format(ID_MISMATCH, i), this.mockedPeople[i].getId(), extractedCollection.get(i).getId());
        }
    }

    @Test
    public void testAddPerson() throws OperationNotSupportedException {
        this.databaseExtended.add(this.additionalMockedPerson);

        Assert.assertEquals(PEOPLE_ARE_DIFFERENT_MESSAGE, this.additionalMockedPerson,this.extractedCollection.get(this.extractedCollection.size() - 1));
    }

    //TODO remove and fetch are going to look like the ones in the "TestDatabase" class. Would have been easier if I had written the tests for generic type of data

    @Test
    public void testFindByUserName() throws OperationNotSupportedException {
        Person foundPerson = this.databaseExtended.findByUsername(MOCKED_PERSON2_NAME);
        Assert.assertEquals(PEOPLE_ARE_DIFFERENT_MESSAGE, this.mockedPeople[1], foundPerson);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUserNameNotFound() throws OperationNotSupportedException {
        this.databaseExtended.findByUsername("aaaa");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUserNameNullNameGiven() throws OperationNotSupportedException {
        this.databaseExtended.findByUsername(null);
    }

    @Test
    public void findUserById() throws OperationNotSupportedException {
        Person foundPerson = this.databaseExtended.findById(MOCKED_PERSON2_ID);
        Assert.assertEquals(PEOPLE_ARE_DIFFERENT_MESSAGE, this.mockedPeople[1], foundPerson);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findUserByIdNotFound() throws OperationNotSupportedException {
        this.databaseExtended.findById(-1);
    }

}
