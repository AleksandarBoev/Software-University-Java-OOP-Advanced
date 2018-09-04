package onehitdungeon.core;

import onehitdungeon.interfaces.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class HeroTrainerImplTest {
    private HeroTrainer heroTrainer;

    private WeaponItem paladinWeapon;
    private OffhandItem paladintOffhand;
    private ArmorItem paladinArmor;

    private Hero paladin;
    private Hero mage;

    //TODO things to test:
    //1-paladins and mages item power ups and reward increases are correct!
    //2-
    //3-
    //4-
    //5-
    //6-
    //7-

    @Before
    public void init() {
        this.heroTrainer = new HeroTrainerImpl();

        this.paladinWeapon = Mockito.mock(WeaponItem.class);
        this.paladintOffhand = Mockito.mock(OffhandItem.class);
        this.paladinArmor = Mockito.mock(ArmorItem.class);

        Mockito.when(this.paladinWeapon.getClass().getSimpleName()).thenReturn("Weapon"); //TODO this is what screwed me up!!!
        Mockito.when(this.paladintOffhand.getClass().getSimpleName()).thenReturn("Offhand");
        Mockito.when(this.paladinArmor.getClass().getSimpleName()).thenReturn("Weapon");
        Mockito.when(this.paladinWeapon.getBattlePower()).thenReturn(20);
        Mockito.when(this.paladinWeapon.getPriceForUpgrade()).thenReturn(15.0);

        Mockito.when(this.paladintOffhand.getBattlePower()).thenReturn(10);
        Mockito.when(this.paladintOffhand.getPriceForUpgrade()).thenReturn(10.0);

        Mockito.when(this.paladinArmor.getBattlePower()).thenReturn(25);
        Mockito.when(this.paladinArmor.getPriceForUpgrade()).thenReturn(20.0);

        this.paladin = Mockito.mock(Hero.class);
        Mockito.when(this.paladin.getHeroClass()).thenReturn("PaladinHero");
        Mockito.when(this.paladin.getWeapon()).thenReturn(this.paladinWeapon);
        Mockito.when(this.paladin.getOffhand()).thenReturn(this.paladintOffhand);
        Mockito.when(this.paladin.getArmor()).thenReturn(this.paladinArmor);


        this.mage = Mockito.mock(Hero.class);
        Mockito.when(this.mage.getHeroClass()).thenReturn("MageHero");
        Mockito.when(this.mage.getWeapon()).thenReturn(this.paladinWeapon);
        Mockito.when(this.mage.getOffhand()).thenReturn(this.paladintOffhand);
        Mockito.when(this.mage.getArmor()).thenReturn(this.paladinArmor);
    }

    @Test
    public void testPaladinWeaponUpgrade() {
        this.heroTrainer.trainHero(this.paladin);
        int expectedValue = 32; //did the calculations by hand
        int actualValue = this.paladinWeapon.getBattlePower();
        //TODO could the problem be when i myself used reflection to access the method for incrementing the hero level?
        //no, I removed it and program still crashes
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void trainHero() {
        //put("paladin-weapon-battlepower", 60);
        this.heroTrainer.trainHero(this.paladin);
    }
}
