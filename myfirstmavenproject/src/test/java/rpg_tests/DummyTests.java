package rpg_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.models.Dummy;

public class DummyTests {
    private static final int DUMMY_HEALTH = 10;
    private static final int DUMMY_XP = 10;
    private static final int EXPECTED_DUMMY_HEALTH_AFTER_ATTACK = 5;

    private Dummy dummy;

    @Before
    public void initializeTestObject() {
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_XP);
    }

    //•	Dummy loses health if attacked
    //•	Dead Dummy throws exception if attacked
    //•	Dead Dummy can give XP
    //•	Alive Dummy can't give XP

    @Test
    public void dummyLosesHealthAfterBeingAttacked() {
        //        Dummy dummy = new Dummy(10, 10);
        this.dummy.takeAttack(5);
        Assert.assertEquals("Health after getting attacked is NOT the correct value", dummy.getHealth(), EXPECTED_DUMMY_HEALTH_AFTER_ATTACK);
    }

    @Test (expected = IllegalStateException.class)
    public void deadDummyThrowsExceptionIfAttacked() {
        //        Dummy dummy = new Dummy(10, 10);
        this.dummy.takeAttack(10);
        this.dummy.takeAttack(10);
    }

    @Test
    public void deadDummyGivesExperience() {
        //        Dummy dummy = new Dummy(10, 10);
        this.dummy.takeAttack(DUMMY_HEALTH);
        Assert.assertTrue("Experience given is not bigger than 0", this.dummy.giveExperience() > 0);
    }

    @Test (expected = IllegalStateException.class)
    public void aliveDummyDoesNotGiveExperience() {
//        Dummy dummy = new Dummy(10, 10);
        this.dummy.giveExperience();
    }
}
