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

public class DummyTests {
    private static final String DUMMY_SHOULD_BE_DEAD_MESSAGE = "Dummy has 0 or less health and SHOULD be dead";
    private static final String DUMMY_SHOULD_BE_ALIVE_MESSAGE = "Dummy has more than 0 health and SHOULD be alive";
    //•	Dummy loses health if attacked
    //•	Dead Dummy throws exception if attacked
    //•	Dead Dummy can give XP
    //•	Alive Dummy can't give XP
    private Target dummy;
    private Target deadDummy;
    private Weapon mockedWeapon; //not really useful for now

    @Before
    public void init() {
        this.dummy = new Dummy(Constants.TARGET_HEALTH, Constants.TARGET_XP);
        this.deadDummy = new Dummy(Constants.ZERO, Constants.TARGET_XP);

        this.mockedWeapon = Mockito.mock(Axe.class);
        Mockito.when(this.mockedWeapon.getAttackPoints()).thenReturn(Constants.WEAPON_DAMAGE);
//        Mockito.when(this.mockedWeapon.getDurabilityPoints()).thenReturn(Constants.WEAPON_DURABILITY);
    }

    @Test
    public void testGetHealth() throws NoSuchFieldException, IllegalAccessException {
        Field healthField = this.dummy.getClass().getDeclaredField("health");
        healthField.setAccessible(true);
        int expectedValue = (Integer)healthField.get(this.dummy);
        healthField.setAccessible(false);

        int actualValue = this.dummy.getHealth();

        Assert.assertEquals(Constants.EXPECTED_VALUE_AND_ACTUAL_VALUE_NOT_EQUAL_MESSAGE, expectedValue, actualValue);
    }

    @Test
    public void testDummyIsDeadIfItHasMoreThanZeroHealth() {
        Assert.assertTrue(DUMMY_SHOULD_BE_ALIVE_MESSAGE, this.dummy.isDead() == false);
    }

    @Test
    public void testDummyIsDeadIfItHasLessThanOrEqualToZeroHealth() {
        Assert.assertTrue(DUMMY_SHOULD_BE_DEAD_MESSAGE, this.deadDummy.isDead() == true);
    }

    @Test
    public void testDummyLoosesHealthAfterGettingAttacked() {
        int expectedHealthAfterAttack = this.dummy.getHealth() - mockedWeapon.getAttackPoints();

        this.dummy.takeAttack(this.mockedWeapon.getAttackPoints());
        int actualHealthAfterAttack = this.dummy.getHealth();

        Assert.assertEquals(Constants.EXPECTED_VALUE_AND_ACTUAL_VALUE_NOT_EQUAL_MESSAGE, expectedHealthAfterAttack, actualHealthAfterAttack);
    }

    @Test
    public void testDummyGivesExperiencePointsIfDead() {
        Assert.assertEquals(Constants.EXPECTED_VALUE_AND_ACTUAL_VALUE_NOT_EQUAL_MESSAGE, Constants.TARGET_XP, this.deadDummy.giveExperience());
    }

    @Test(expected = IllegalStateException.class)
    public void testDummyGivesExperiencePointsIfNotDead() {
        this.dummy.giveExperience();
    }


    @Test
    public void testDropRandomWeapon() { // not sure how to test such a thing

    }
}
