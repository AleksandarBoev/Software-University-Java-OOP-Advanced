package p02_extendedDatabaseTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p02_extendedDatabase.contracts.Person;
import p02_extendedDatabase.models.PersonImpl;

import java.lang.reflect.Field;

public class TestPerson {
    private static long PERSON_ID = 1;
    private static String PERSON_NAME = "Pesho";
    private static final String ID_MISMATCH = "Id mismatch!";
    private static final String NAME_MISMATCH = "Name mismatch!";

    private PersonImpl person;

    @Before
    public void init() {
        this.person = new PersonImpl(PERSON_ID, PERSON_NAME);
    }

    @Test
    public void testPersonConstructorWithIdAndName() throws NoSuchFieldException, IllegalAccessException {
        long expectedId = PERSON_ID;
        String expectedName = PERSON_NAME;

        Field idField = this.person.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        long actualId = (long)idField.get(this.person);
        idField.setAccessible(false);

        Field nameField = this.person.getClass().getDeclaredField("name");
        nameField.setAccessible(true);
        String actualName = (String)nameField.get(this.person);
        nameField.setAccessible(false);

        Assert.assertEquals(ID_MISMATCH, expectedId, actualId);
        Assert.assertEquals(NAME_MISMATCH, expectedName, actualName);
    }

    @Test
    public void testNameGetter() {
        Assert.assertEquals(NAME_MISMATCH, PERSON_NAME, this.person.getName());
    }

    @Test
    public void testIdGetter() {
        Assert.assertEquals(ID_MISMATCH, PERSON_ID, this.person.getId());
    }
}
