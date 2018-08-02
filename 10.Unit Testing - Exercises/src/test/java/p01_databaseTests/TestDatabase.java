package p01_databaseTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p01_database.contracts.Database;
import p01_database.models.DatabaseImpl;

import javax.naming.OperationNotSupportedException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestDatabase {
    private static final int[] DATABASE_ELEMENTS = {1, 2, 3, 4, 5};
    private static final int[] EMPTY_DATABASE_ELEMENTS = {};
    private static final int[] FULL_DATABASE_ELEMENTS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    private static final String ELEMENTS_FROM_INDEX_ARE_NOT_EQUAL_MESSAGE = "Elements with index %d from both collections are NOT equal!";
    private static final String ELEMENTS_ARE_NOT_EQUAL_MESSAGE = "Expected value and returned value are NOT equal!";
    private static final int ADD_ELEMENT_VALUE = 100;
    private static final String INCORRECT_ELEMENT_REMOVED_MESSAGE = "Incorrect element has been removed!";

    private Database database;
    private Database emptyDatabase;
    private Database fullDataBase;
    private List<Integer> extractedCollection;

    @Before
    public void init() throws OperationNotSupportedException, NoSuchFieldException, IllegalAccessException {
        this.database = new DatabaseImpl(DATABASE_ELEMENTS);
        this.emptyDatabase = new DatabaseImpl(EMPTY_DATABASE_ELEMENTS);
        this.fullDataBase = new DatabaseImpl(FULL_DATABASE_ELEMENTS);

        Field collectionField = this.database.getClass().getDeclaredField("collection");
        collectionField.setAccessible(true);
        this.extractedCollection = (ArrayList<Integer>)collectionField.get(this.database);
        collectionField.setAccessible(false);
        //extractedCollection and the database collection are refferant and point to the same place in the memory.
        //So testing changes to extractedCollection is like testing the field itself, without having to modify access modifiers or create getters.
    }

    @Test
    public void testConstructor() throws OperationNotSupportedException, NoSuchFieldException, IllegalAccessException { //is it okay to test two things at once?
        Iterator iterator = this.extractedCollection.iterator();

        int counter = 0;
        while (iterator.hasNext()) {
            int currentNumberFromList = (Integer) iterator.next();
            Assert.assertEquals(String.format(ELEMENTS_FROM_INDEX_ARE_NOT_EQUAL_MESSAGE, counter) ,currentNumberFromList, DATABASE_ELEMENTS[counter]);
            counter++;
        }

        Assert.assertEquals("Sizes are NOT equal!", counter,this.extractedCollection.size());
    }

    @Test
    public void testAddElementToNonFullCollection() throws OperationNotSupportedException {
        this.database.add(ADD_ELEMENT_VALUE);
        int lastValue = this.extractedCollection.get(this.extractedCollection.size() - 1);

        Assert.assertEquals(ELEMENTS_ARE_NOT_EQUAL_MESSAGE, lastValue, ADD_ELEMENT_VALUE);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddElementToFullCollection() throws OperationNotSupportedException {
        this.fullDataBase.add(ADD_ELEMENT_VALUE);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddNullElement() throws OperationNotSupportedException {
        this.database.add(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveElementFromEmptyCollection() throws OperationNotSupportedException {
        this.emptyDatabase.remove();
    }

    @Test
    public void testRemoveElementFromNonEmptyCollection() throws OperationNotSupportedException {
        int expectedSize = this.extractedCollection.size() - 1;

        this.database.remove();

        int actualSize = this.extractedCollection.size();

        Assert.assertEquals(ELEMENTS_ARE_NOT_EQUAL_MESSAGE, expectedSize, actualSize);
    }

    @Test
    public void testCorrectElementHasBeenRemovedFromNonEmptyCollection() throws OperationNotSupportedException {
        int removedElement = this.extractedCollection.get(this.extractedCollection.size() - 1); //last element should be the one removed
        this.database.remove();

        for (Integer integer : this.extractedCollection) { //I have created a collection with all UNIQUE numbers. That's why this test is correct
            Assert.assertTrue(INCORRECT_ELEMENT_REMOVED_MESSAGE, integer != removedElement);
        }
    }

    @Test
    public void testReturnedArrayFromFetch() {
        Integer[] returnedArray = this.database.fetch();
        Assert.assertEquals(ELEMENTS_ARE_NOT_EQUAL_MESSAGE, this.extractedCollection.size(), returnedArray.length);


        for (int i = 0; i < returnedArray.length; i++) {
            Assert.assertEquals(String.format(ELEMENTS_FROM_INDEX_ARE_NOT_EQUAL_MESSAGE, i), this.extractedCollection.get(i), returnedArray[i]);
        }
    }

}
