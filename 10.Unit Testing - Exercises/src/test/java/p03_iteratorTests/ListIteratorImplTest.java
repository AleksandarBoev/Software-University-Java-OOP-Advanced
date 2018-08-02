package p03_iteratorTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p03_iterator.ListIterator;
import p03_iterator.ListIteratorImpl;

import javax.naming.OperationNotSupportedException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListIteratorImplTest<T> {
    private static final List<String> COLLECTION = new ArrayList<>(Arrays.asList("111", "222", "333"));
    private static final List<String> EMPTY_COLLECTION = new ArrayList<>(Arrays.asList());

    private ListIterator<String> listIterator;

    @Before
    public void init() throws OperationNotSupportedException {
        this.listIterator = new ListIteratorImpl<>(COLLECTION);
    }

    @Test
    public void testConstructor() throws NoSuchFieldException, IllegalAccessException {
        Field collectionField = this.listIterator.getClass().getDeclaredField("collection");
        collectionField.setAccessible(true);
        List<String> extractedCollection = (ArrayList)collectionField.get(this.listIterator);
        collectionField.setAccessible(false);

        for (int i = 0; i < extractedCollection.size(); i++) {
            Assert.assertEquals(extractedCollection.get(i), COLLECTION.get(i));
        }

        Assert.assertEquals(extractedCollection.size(), COLLECTION.size());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorWithNullParameterPassed() throws OperationNotSupportedException {
        this.listIterator = new ListIteratorImpl<>(null);
    }

    @Test
    public void testMove() throws NoSuchFieldException, IllegalAccessException {
        Field cursorField = this.listIterator.getClass().getDeclaredField("cursor");

        Assert.assertTrue(this.listIterator.move());

        cursorField.setAccessible(true);
        int actualValue = (int) cursorField.get(this.listIterator);
        cursorField.setAccessible(false);

        int expectedValue = 1;

        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testMoveEmptyCollection() throws OperationNotSupportedException {
        this.listIterator = new ListIteratorImpl<>(EMPTY_COLLECTION);

        Assert.assertTrue(!this.listIterator.move());
    }

    @Test
    public void testHasNext() {
        Assert.assertTrue(this.listIterator.hasNext());
    }

    @Test
    public void testHasNextEmptyCollection() throws OperationNotSupportedException {
        this.listIterator = new ListIteratorImpl<>(EMPTY_COLLECTION);
        Assert.assertTrue(!this.listIterator.hasNext());
    }

    @Test
    public void testPrint() {
        String expectedValue = COLLECTION.get(0);
        String actualValue = this.listIterator.getCurrentElementAsString();

        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test(expected = IllegalStateException.class)
    public void testPrintOnEmptyCollection() throws OperationNotSupportedException {
        this.listIterator = new ListIteratorImpl<>(EMPTY_COLLECTION);
        this.listIterator.getCurrentElementAsString();
    }

}
