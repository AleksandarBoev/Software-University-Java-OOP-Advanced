package rpg_tests;

import org.junit.Assert;
import org.junit.Test;
import rpg_lab.Axe;
import rpg_lab.Dummy;

import java.lang.reflect.Field;

public class AxeTests {
    //•	Test if weapon loses durability after each attack
    //•	Test attacking with a broken weapon
    @Test
    public void axeLosesDurabilityAfterAttack() throws NoSuchFieldException, IllegalAccessException {
        Axe axe = new Axe(10, 10);
        axe.attack(new Dummy(10, 10));

        Field durabilityField = Axe.class.getDeclaredField("durabilityPoints"); //or just use the getter, but I think this is the better way
        durabilityField.setAccessible(true);
        int durabilityAfterAttack = (Integer) durabilityField.get(axe);
        durabilityField.setAccessible(false);

//        Assert.assertTrue(9 == durabilityAfterAttack);
        Assert.assertEquals("Durability is NOT the correct value after attacking!", 9, durabilityAfterAttack); //both work
    }

    @Test (expected = IllegalStateException.class)
    public void attackWithBrokenWeapon() {
        Axe axe = new Axe(10, 2);
        Dummy dummy = new Dummy(300, 10);
        axe.attack(dummy);
        axe.attack(dummy);
        axe.attack(dummy);
        axe.attack(dummy);
    }
}
