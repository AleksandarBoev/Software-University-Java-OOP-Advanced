package cresla.entities.containers;

import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Container;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Module;

import java.util.*;

public class ModuleContainer implements Container {
    private int moduleCapacity;
    private LinkedList<Module> modulesByInput; //all modules are here
    private Map<Integer, EnergyModule> energyModules; //some of them are here
    private Map<Integer, AbsorbingModule> absorbingModules; //and the rest - here

    public ModuleContainer(int moduleCapacity) { //DI not necessary here
        this.moduleCapacity = moduleCapacity;
        this.modulesByInput = new LinkedList<>();
        this.energyModules = new LinkedHashMap<Integer, EnergyModule>();
        this.absorbingModules = new LinkedHashMap<Integer, AbsorbingModule>();
    }

    @Override
    public void addEnergyModule(EnergyModule energyModule) { //the problem was energyModule != null
        if (energyModule == null) {
            throw new IllegalArgumentException();
        }

        if (this.modulesByInput.size() == this.moduleCapacity) {
            this.removeOldestModule(); //now there is room for another module, but what if modeuleCapacity is 0?
        }

        this.energyModules.put(energyModule.getId(), energyModule);
        this.modulesByInput.addLast(energyModule);
    }

    @Override
    public void addAbsorbingModule(AbsorbingModule absorbingModule) { //the problem was absorbingModule != null
        if (absorbingModule == null) {
            throw new IllegalArgumentException();
        }

        if (this.modulesByInput.size() == this.moduleCapacity) {
            this.removeOldestModule();
        }

        this.absorbingModules.put(absorbingModule.getId(), absorbingModule);
        this.modulesByInput.addLast(absorbingModule);
    }

    @Override
    public long getTotalEnergyOutput() {//could have been a potential problem
        return this.energyModules.values().stream()
                .mapToLong(EnergyModule::getEnergyOutput)
                .sum();
    }

    //A Reactor has an energy output – equal to the sum of the energyOutputs of all its Energy Modules.
    //A Reactor also has heat absorbing – equal to the sum of the heatAbsorbings of all its Absorbing Modules.

    @Override
    public long getTotalHeatAbsorbing() { //could have been a potential problem
        return this.absorbingModules.values().stream()
                .mapToLong(AbsorbingModule::getHeatAbsorbing)
                .sum();
    }

    private void removeOldestModule() {
        int removeId = this.modulesByInput.removeFirst().getId(); //remove and take id

        if(this.energyModules.containsKey(removeId)) { //there was a problem here
            this.energyModules.remove(removeId);
            return;
        }

        if(this.absorbingModules.containsKey(removeId)) {
            this.absorbingModules.remove(removeId);
        }
    }
}