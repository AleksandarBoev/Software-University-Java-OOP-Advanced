package rpg_tests_mockito;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.mockito.Mockito;
import org.mockito.Mockito;
import rpg_lab.contracts.RpgCharacter;
import rpg_lab.contracts.Target;
import rpg_lab.contracts.Weapon;
import rpg_lab.models.Axe;
import rpg_lab.models.Dummy;
import rpg_lab.models.Hero;
import rpg_lab.models.Sword;

import java.lang.reflect.Field;

public class HeroTests {
    private static String heroName = "Pesho";

    private RpgCharacter hero;
    private Target dummy;

    @Before
    public void init() {
        Weapon startingWeapon = Mockito.mock(Axe.class);
        this.hero = new Hero("Pesho", startingWeapon);
        Weapon mockedWeapon = Mockito.mock(Axe.class);

        this.dummy = Mockito.mock(Dummy.class);
        Mockito.when(this.dummy.giveExperience()).thenReturn(Constants.TARGET_XP);
        Mockito.when(this.dummy.isDead()).thenReturn(true);
        Weapon mockedWeaponDropped = Mockito.mock(Sword.class);
        Mockito.when(this.dummy.dropRandomWeapon()).thenReturn(mockedWeaponDropped);
    }

    @Test
    public void killingATargetGainsCorrectAmountOfExperience() throws NoSuchFieldException, IllegalAccessException {
        Field experienceField = Hero.class.getDeclaredField("experience");
        experienceField.setAccessible(true);

        int initialHeroExperience = (Integer)experienceField.get(this.hero);

        this.hero.attack(this.dummy);

        int expectedExperienceAfterKill = initialHeroExperience + Constants.TARGET_XP;
        int actualExperienceAfterKill = (Integer)experienceField.get(this.hero);
        experienceField.setAccessible(false);
        Assert.assertEquals("Hero did NOT gain expected experience", expectedExperienceAfterKill, actualExperienceAfterKill);
    }



    @Test
    public void weaponIsAddedToCollection() {
        int expectedLootCount = 2;

        int actualLootCount = 0;
        Weapon loot = this.dummy.dropRandomWeapon();
        this.hero.addWeapon(loot);
        for (Weapon weapon : this.hero.getWeapons()) {
            actualLootCount++;
        }
        actualLootCount++;

        Assert.assertEquals(Constants.EXPECTED_VALUE_AND_ACTUAL_VALUE_NOT_EQUAL_MESSAGE, expectedLootCount, actualLootCount);
    }

}
