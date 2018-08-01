package rpg_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.models.Axe;
import rpg_lab.models.Dummy;

import java.lang.reflect.Field;

public class AxeTests {
    //•	Test if weapon loses durability after each attack
    //•	Test attacking with a broken weapon
    private static final int ATTACK = 10;
    private static final int DURABILITY = 10;
    private static final int DUMMY_HEALTH = 10; //duplication code, but I'm too lazy to make a package constants
    private static final int DUMMY_XP = 10;

    private Axe axe;
    private Dummy dummy;

    @Before
    public void initialize() {
        this.axe = new Axe(ATTACK, DURABILITY);
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_XP);
    }

    @Test
    public void axeLosesDurabilityAfterAttack() throws NoSuchFieldException, IllegalAccessException {
//        Axe axe = new Axe(10, 10);
//        axe.attack(new Dummy(10, 10));

        this.axe.attack(this.dummy);

        Field durabilityField = Axe.class.getSuperclass().getDeclaredField("durabilityPoints"); //or just use the getter, but I think this is the better way
        durabilityField.setAccessible(true);
        int durabilityAfterAttack = (Integer) durabilityField.get(axe);
        durabilityField.setAccessible(false);

//        Assert.assertTrue(9 == durabilityAfterAttack);
        Assert.assertEquals("Durability is NOT the correct value after attacking!", 9, durabilityAfterAttack); //both work
    }

    @Test (expected = IllegalStateException.class)
    public void attackWithBrokenWeapon() {
        this.axe = new Axe(10, 2); //overriding the axe and dummy values for the specific test purpose.
        this.dummy = new Dummy(300, 10);
        this.axe.attack(this.dummy);
        this.axe.attack(this.dummy);
        this.axe.attack(this.dummy);
    }
}
