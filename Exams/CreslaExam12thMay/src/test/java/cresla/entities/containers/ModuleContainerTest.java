package cresla.entities.containers;


import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Container;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Module;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class ModuleContainerTest {
    private static final String EXPECTED_AND_ACTUAL_VALUE_MISMATCH_MESSAGE = "Expected %1$s and actual %1$s are not equal!";
    private static final String NULL_MESSAGE = "%s is null!";
    private static final int MAX_INT = Integer.MAX_VALUE;

    private static final int MODULE_CAPACITY = 4;

    private Container moduleContainer;

    //private int moduleCapacity;
    //    private LinkedList<Module> modulesByInput; //all modules are here
    //    private Map<Integer, EnergyModule> energyModules; //some of them are here
    //    private Map<Integer, AbsorbingModule> absorbingModules; //and the rest - here
    private int moduleCapacityExtracted;
    private LinkedList<Module> modulesByInputExtracted;
    private Map<Integer, EnergyModule> energyModulesExtracted;
    private Map<Integer, AbsorbingModule> absorbingModulesExtracted;

    @Before
    public void init() throws NoSuchFieldException, IllegalAccessException {
        this.moduleContainer = new ModuleContainer(MODULE_CAPACITY);

        Field moduleCapacityField = this.moduleContainer.getClass().getDeclaredField("moduleCapacity");
        moduleCapacityField.setAccessible(true);
        this.moduleCapacityExtracted = (int)moduleCapacityField.get(this.moduleContainer);
        moduleCapacityField.setAccessible(false);

        Field modulesByInputField = this.moduleContainer.getClass().getDeclaredField("modulesByInput");
        modulesByInputField.setAccessible(true);
        this.modulesByInputExtracted = (LinkedList<Module>)modulesByInputField.get(this.moduleContainer);
        modulesByInputField.setAccessible(false);

        Field energyModulesField = this.moduleContainer.getClass().getDeclaredField("energyModules");
        energyModulesField.setAccessible(true);
        this.energyModulesExtracted = (LinkedHashMap<Integer, EnergyModule>)energyModulesField.get(this.moduleContainer);
        energyModulesField.setAccessible(false);

        Field absorbingModulesField = this.moduleContainer.getClass().getDeclaredField("absorbingModules");
        absorbingModulesField.setAccessible(true);
        this.absorbingModulesExtracted = (LinkedHashMap<Integer, AbsorbingModule>)absorbingModulesField.get(this.moduleContainer);
        absorbingModulesField.setAccessible(false);
    }

//    @Test
//    public void testConstructor() {
//        Assert.assertEquals(String.format(EXPECTED_AND_ACTUAL_VALUE_MISMATCH_MESSAGE, "module capacity"),
//                MODULE_CAPACITY, this.moduleCapacityExtracted);
//
//        Assert.assertTrue(String.format(NULL_MESSAGE, "modulesByInput"), this.modulesByInputExtracted != null);
//        Assert.assertTrue(String.format(NULL_MESSAGE, "energyModules"), this.energyModulesExtracted != null);
//        Assert.assertTrue(String.format(NULL_MESSAGE, "absorbingModules"), this.absorbingModulesExtracted != null);
//    }

    @Test(expected = IllegalArgumentException.class)
    public void addEnergyModule_NullTest() {
        this.moduleContainer.addEnergyModule(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAbsorbingModule_NullTest() {
        this.moduleContainer.addAbsorbingModule(null);
    }

    @Test
    public void getTotalEnergyOutput_IntegerOverflowTest() {
        long expectedValue = 2L * MAX_INT;

        EnergyModule mockedEnergyModule1 = Mockito.mock(EnergyModule.class);
        Mockito.when(mockedEnergyModule1.getEnergyOutput()).thenReturn(MAX_INT);
        Mockito.when(mockedEnergyModule1.getId()).thenReturn(1); //they are being put in a map, with the id being the key

        EnergyModule mockedEnergyModule2 = Mockito.mock(EnergyModule.class);
        Mockito.when(mockedEnergyModule2.getEnergyOutput()).thenReturn(MAX_INT);
        Mockito.when(mockedEnergyModule2.getId()).thenReturn(2);

        this.moduleContainer.addEnergyModule(mockedEnergyModule1);
        this.moduleContainer.addEnergyModule(mockedEnergyModule2);

        long actualValue = this.moduleContainer.getTotalEnergyOutput();

        Assert.assertEquals(String.format(EXPECTED_AND_ACTUAL_VALUE_MISMATCH_MESSAGE, "total energy output"),
                expectedValue, actualValue);
    }

    @Test
    public void getTotalHeatAbsorbing_IntegerOverflowTest() {
        long expectedValue = 2L * MAX_INT;

        AbsorbingModule mockedAbsorbingModule1 = Mockito.mock(AbsorbingModule.class);
        Mockito.when(mockedAbsorbingModule1.getHeatAbsorbing()).thenReturn(MAX_INT);
        Mockito.when(mockedAbsorbingModule1.getId()).thenReturn(1); //they are being put in a map, with the id being the key

        AbsorbingModule mockedAbsorbingModule2 = Mockito.mock(AbsorbingModule.class);
        Mockito.when(mockedAbsorbingModule2.getHeatAbsorbing()).thenReturn(MAX_INT);
        Mockito.when(mockedAbsorbingModule2.getId()).thenReturn(2);

        this.moduleContainer.addAbsorbingModule(mockedAbsorbingModule1);
        this.moduleContainer.addAbsorbingModule(mockedAbsorbingModule2);

        long actualValue = this.moduleContainer.getTotalHeatAbsorbing();

        Assert.assertEquals(String.format(EXPECTED_AND_ACTUAL_VALUE_MISMATCH_MESSAGE, "total heat absorbing"),
                expectedValue, actualValue);
    }

    @Test
    public void addModule_ModuleCapacityOverExceed() {
        int idCounter = 2;

        AbsorbingModule mockedAbsorbingModule1 = Mockito.mock(AbsorbingModule.class);
        Mockito.when(mockedAbsorbingModule1.getId()).thenReturn(idCounter++); //they are being put in a map, with the id being the key

        AbsorbingModule mockedAbsorbingModule2 = Mockito.mock(AbsorbingModule.class);
        Mockito.when(mockedAbsorbingModule2.getId()).thenReturn(idCounter++);

        EnergyModule mockedEnergyModule1 = Mockito.mock(EnergyModule.class);
        Mockito.when(mockedEnergyModule1.getId()).thenReturn(idCounter++);

        EnergyModule mockedEnergyModule2 = Mockito.mock(EnergyModule.class);
        Mockito.when(mockedEnergyModule2.getId()).thenReturn(idCounter++);

        this.moduleContainer.addAbsorbingModule(mockedAbsorbingModule1);
        this.moduleContainer.addEnergyModule(mockedEnergyModule1);
        this.moduleContainer.addAbsorbingModule(mockedAbsorbingModule2);
        this.moduleContainer.addEnergyModule(mockedEnergyModule2);

        //Now the module capacity is at its limit.
        // Adding an energy module should remove the firstly added Module, which is an Absorbing Module
        EnergyModule mockedEnergyModule3 = Mockito.mock(EnergyModule.class);
        Mockito.when(mockedEnergyModule3.getId()).thenReturn(idCounter++);
        this.moduleContainer.addEnergyModule(mockedEnergyModule3);

        int expectedEnergyModulesCapacity = 3;
        int actualEnergyModulesCapacity = this.energyModulesExtracted.size();

        Assert.assertEquals(String.format(EXPECTED_AND_ACTUAL_VALUE_MISMATCH_MESSAGE, "energy module capacity"),
                expectedEnergyModulesCapacity, actualEnergyModulesCapacity);

        int expectedAbsorbingModulesCapacity = 1;
        int actualAbsorbingModulesCapacity = this.absorbingModulesExtracted.size();

        Assert.assertEquals(String.format(EXPECTED_AND_ACTUAL_VALUE_MISMATCH_MESSAGE, "absorbing module capacity"),
                expectedAbsorbingModulesCapacity, actualAbsorbingModulesCapacity);

        //Now adding an Absorbing module should remove the secondly added Module in the beginning, which is an Energy Module
        AbsorbingModule mockedAbsorbingModule3 = Mockito.mock(AbsorbingModule.class);
        Mockito.when(mockedAbsorbingModule3.getId()).thenReturn(idCounter++);

        this.moduleContainer.addAbsorbingModule(mockedAbsorbingModule3);

        expectedEnergyModulesCapacity = 2;
        actualEnergyModulesCapacity = this.energyModulesExtracted.size();

        Assert.assertEquals(String.format(EXPECTED_AND_ACTUAL_VALUE_MISMATCH_MESSAGE, "energy module capacity"),
                expectedEnergyModulesCapacity, actualEnergyModulesCapacity);

        expectedAbsorbingModulesCapacity = 2;
        actualAbsorbingModulesCapacity = this.absorbingModulesExtracted.size();

        Assert.assertEquals(String.format(EXPECTED_AND_ACTUAL_VALUE_MISMATCH_MESSAGE, "absorbing module capacity"),
                expectedAbsorbingModulesCapacity, actualAbsorbingModulesCapacity);
    }

}
