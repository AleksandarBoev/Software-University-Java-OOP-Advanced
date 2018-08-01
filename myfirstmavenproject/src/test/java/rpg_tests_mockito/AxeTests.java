package rpg_tests_mockito;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import rpg_lab.contracts.Target;
import rpg_lab.contracts.Weapon;
import rpg_lab.models.Axe;
import rpg_lab.models.Dummy;

import java.lang.reflect.Field;

public class AxeTests {
    private Weapon axe;

    @Before
    public void init() {
        this.axe = new Axe(Constants.WEAPON_DAMAGE, Constants.WEAPON_DURABILITY);
    }

    @Test
    public void testGetAttackPoints() {
        int expectedAttackPoints = Constants.WEAPON_DAMAGE;
        int actualAttackPoints = this.axe.getAttackPoints();

        Assert.assertEquals(Constants.EXPECTED_VALUE_AND_ACTUAL_VALUE_NOT_EQUAL_MESSAGE, expectedAttackPoints, actualAttackPoints);
    }

    @Test
    public void testGetDurabilityPoints() throws NoSuchFieldException, IllegalAccessException {
        Field durabilityField = this.axe.getClass().getSuperclass().getDeclaredField("durabilityPoints"); //second way of testing. Not sure which one is more correct.
        durabilityField.setAccessible(true);
        int expectedDurability = (Integer)durabilityField.get(this.axe);
        durabilityField.setAccessible(false);

        int actualDurability = this.axe.getDurabilityPoints();

        Assert.assertEquals(Constants.EXPECTED_VALUE_AND_ACTUAL_VALUE_NOT_EQUAL_MESSAGE, expectedDurability, actualDurability);
    }

    @Test
    public void testDurabilityLossAfterAttacking() { //test passes, but there are warnings, because of the fakeDummy
        Target fakeDummy = Mockito.mock(Dummy.class);
        //no need to use reflection to get the durability field value, because the getter has been tested and works properly
        int expectedDurabilityAfterAttack = this.axe.getDurabilityPoints() - 1;

        this.axe.attack(fakeDummy);
        int actualDurabilityAfterAttack = this.axe.getDurabilityPoints();

        Assert.assertEquals(Constants.EXPECTED_VALUE_AND_ACTUAL_VALUE_NOT_EQUAL_MESSAGE, expectedDurabilityAfterAttack, actualDurabilityAfterAttack);
    }

    @Test(expected = IllegalStateException.class)
    public void testWeaponGettingBrokenAfterAttackingManyTimes() {
        //if more tests with a target are made, it wouldn't be a bad idea to add a Target field and to make the fake dummy in the "init" method above
        Target fakeDummy = Mockito.mock(Dummy.class);

        for (int i = 0; i < Constants.WEAPON_DURABILITY + 1; i++) {
            this.axe.attack(fakeDummy);
        }
    }
}
