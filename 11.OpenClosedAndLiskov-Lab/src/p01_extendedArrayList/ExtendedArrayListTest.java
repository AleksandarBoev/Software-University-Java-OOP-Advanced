package p01_extendedArrayList;

import org.junit.Assert;
import org.junit.Test;

public class ExtendedArrayListTest { //how do I test generic methods?
    private ExtendedArrayList<Integer> integers;

    @Test
    public void testMinIfInMiddle() {
        this.integers = new ExtendedArrayList<>(new Integer[]{9, 3, 5, 9, 8, 0, 10, 22, 23});

        Integer expectedMinElement = 0;

        Integer actualMinElement = this.integers.getMin();

        Assert.assertEquals(expectedMinElement, actualMinElement);
    }

    @Test
    public void testMinIfInStart() {
        this.integers = new ExtendedArrayList<>(new Integer[]{0, 9, 3, 5, 9, 8, 10, 22, 23});

        Integer expectedMinElement = 0;

        Integer actualMinElement = this.integers.getMin();

        Assert.assertEquals(expectedMinElement, actualMinElement);
    }

    @Test
    public void testMinIfInEnd() {
        this.integers = new ExtendedArrayList<>(new Integer[]{9, 3, 5, 9, 8, 10, 22, 23, 0});

        Integer expectedMinElement = 0;

        Integer actualMinElement = this.integers.getMin();

        Assert.assertEquals(expectedMinElement, actualMinElement);
    }

    @Test
    public void testMax() { //no need to test beginning/end because it is a mirror method of the min method
        this.integers = new ExtendedArrayList<>(new Integer[]{9, 3, 5, 9, 8, 10, 22, 100, 0});

        Integer expectedMaxElement = 100;

        Integer actualMaxElement = this.integers.getMax();

        Assert.assertEquals(expectedMaxElement, actualMaxElement);
    }
}
