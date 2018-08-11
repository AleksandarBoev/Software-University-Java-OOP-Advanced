package cresla.entities.abstractClasses;

import cresla.entities.containers.ModuleContainer;
import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Container;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Reactor;

import java.lang.reflect.Field;
import java.util.List;

public abstract class BaseReactor extends BaseIdentifiable implements Reactor {
    private Container moduleContainer;
    //private int moduleCount?

    protected BaseReactor(int id, int moduleCapacity) { //TODO possible additional values
        super(id);
        this.moduleContainer = new ModuleContainer(moduleCapacity); //not sure module count should be like this
    }

    @Override
    public int getModuleCount() {
        //    private LinkedList<Module> modulesByInput; //all modules are here
        Field modulesByInputField = null;
        try {
            modulesByInputField = this.moduleContainer.getClass().getDeclaredField("modulesByInput");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        modulesByInputField.setAccessible(true);
        int modulesCount = 0;
        try {
            List<Module> extractedModulesByInput = (List<Module>) modulesByInputField.get(this.moduleContainer);
            modulesCount = extractedModulesByInput.size();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        modulesByInputField.setAccessible(false);

        return modulesCount;
    }

    @Override
    public void addEnergyModule(EnergyModule energyModule) {
        this.moduleContainer.addEnergyModule(energyModule);
    }

    @Override
    public void addAbsorbingModule(AbsorbingModule absorbingModule) {
        this.moduleContainer.addAbsorbingModule(absorbingModule);
    }

    @Override
    public String toString() {
        //CryoReactor - 1
        //Energy Output: 4000
        //Heat Absorbing: 10000
        //Modules: 5
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(" - ").append(this.getId()).append(System.lineSeparator());

//        long energyOutput = this.getTotalEnergyOutput();
//        if (energyOutput > this.getTotalHeatAbsorbing())
//            energyOutput = 0;

        sb.append("Energy Output: ").append(this.getTotalEnergyOutput()).append(System.lineSeparator());
        sb.append("Heat Absorbing: ").append(this.getTotalHeatAbsorbing()).append(System.lineSeparator());
        sb.append("Modules: ").append(this.getModuleCount());

        return sb.toString();
    }

    protected Container getModuleContainer() {
        return this.moduleContainer;
    }
}
