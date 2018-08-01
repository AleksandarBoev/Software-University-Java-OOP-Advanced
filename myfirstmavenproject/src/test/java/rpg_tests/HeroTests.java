package rpg_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.contracts.RpgCharacter;
import rpg_lab.contracts.Target;
import rpg_lab.contracts.Weapon;
import rpg_lab.models.Axe;
import rpg_lab.models.Dummy;
import rpg_lab.models.Hero;

import java.lang.reflect.Field;

public class HeroTests {
    private static final int AXE_ATTACK = 10;
    private static final int AXE_DURABILITY = 10;
    private static final int DUMMY_HEALTH = 10;
    private static final int DUMMY_XP = 10;

    private RpgCharacter hero;
    private Target dummy;

    @Before
    public void init() {
        Weapon axe = new Axe(AXE_ATTACK, AXE_DURABILITY);
        this.hero = new Hero("Pesho", axe);
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_XP);
    }

    @Test
    public void killingATargetGainsCorrectAmountOfExperience() throws NoSuchFieldException, IllegalAccessException {
        Field experienceField = Hero.class.getDeclaredField("experience");
        experienceField.setAccessible(true);

        int initialHeroExperience = (Integer)experienceField.get(this.hero);

        this.hero.attack(this.dummy);

        int expectedExperienceAfterKill = initialHeroExperience + DUMMY_XP;
        int actualExperienceAfterKill = (Integer)experienceField.get(this.hero);
        experienceField.setAccessible(false);
        Assert.assertEquals("Hero did NOT gain expected experience", expectedExperienceAfterKill, actualExperienceAfterKill);

    }
}
