package cresla.entities.commandManager;

import cresla.entities.containers.ModuleContainer;
import cresla.entities.modules.CooldownSystem;
import cresla.entities.modules.CryogenRod;
import cresla.entities.modules.HeatProcessor;
import cresla.entities.reactors.CryoReactor;
import cresla.entities.reactors.HeatReactor;
import cresla.interfaces.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ManagerImpl implements Manager {
    private int idCounter;
    private Map<Integer, Reactor> idReactor;
    private int energyModuleCount;
    private int absorbingModulesCount;
    private int cryoReactorsCount;
    private int heatReactorsCount;
    private Map<Integer, Identifiable> allReactorsAndModules;
    //o	Energy Modules and Absorbing Modules are all Modules that were registered in the system…
    // Regardless of that whether they were removed in the process, you print them.


    public ManagerImpl() {
        this.idCounter = 1;
        this.idReactor = new HashMap<>();
        this.energyModuleCount = 0;
        this.absorbingModulesCount = 0;
        this.cryoReactorsCount = 0;
        this.heatReactorsCount = 0;
        this.allReactorsAndModules = new HashMap<>();
    }

    @Override
    public String reactorCommand(List<String> arguments) {
        //•	Reactor {reactorType} {additionalParameter} {moduleCapacity}
        String reactorType = arguments.get(1);
        int additionalParameter = Integer.parseInt(arguments.get(2));
        int moduleCapacity = Integer.parseInt(arguments.get(3));
        Container moduleContainer = new ModuleContainer(moduleCapacity);
        int reactorId = this.idCounter;

        Reactor newReactor = null;
        switch (reactorType) {
            case "Cryo":
                newReactor = new CryoReactor(reactorId, moduleContainer, additionalParameter);
                this.incrementCryoReactorsCount();
                break;

            case "Heat":
                newReactor = new HeatReactor(reactorId, moduleContainer, additionalParameter);
                this.incrementHeatReactorsCount();
                break;
        }
        this.incrementId();

        this.idReactor.put(reactorId, newReactor);
        this.allReactorsAndModules.put(reactorId, newReactor);

        return String.format("Created %s Reactor - %d", reactorType, reactorId);
    }

    @Override
    public String moduleCommand(List<String> arguments) {
        //•	Module {reactorId} {type} {additionalParameter}
        int reactorId = Integer.parseInt(arguments.get(1));
        String moduleType = arguments.get(2);
        int additionalParameter = Integer.parseInt(arguments.get(3));
        int moduleId = this.idCounter;

        Reactor reactor = this.idReactor.get(reactorId);

        switch (moduleType) {
            case "CooldownSystem":
                AbsorbingModule cooldownSystem = new CooldownSystem(moduleId, additionalParameter);
                reactor.addAbsorbingModule(cooldownSystem);
                this.allReactorsAndModules.put(moduleId, cooldownSystem);

                this.incrementAbsorbingModulesCount();
                break;

            case "CryogenRod":
                EnergyModule cryogenRod = new CryogenRod(moduleId, additionalParameter);
                reactor.addEnergyModule(cryogenRod);
                this.allReactorsAndModules.put(moduleId, cryogenRod);

                this.incrementEnergyModuleCount();
                break;

            case "HeatProcessor":
                AbsorbingModule heatProcessor = new HeatProcessor(moduleId, additionalParameter);
                reactor.addAbsorbingModule(heatProcessor);
                this.allReactorsAndModules.put(moduleId, heatProcessor);

                this.incrementAbsorbingModulesCount();
                break;
        }

        this.incrementId();
        return String.format("Added %s - %d to Reactor - %d", moduleType, moduleId, reactorId);
    }

    @Override
    public String reportCommand(List<String> arguments) {
        //•	Report {id}
        int id = Integer.parseInt(arguments.get(1));
        Identifiable machine = this.allReactorsAndModules.get(id);

        return machine.toString();
    }

    @Override
    public String exitCommand(List<String> arguments) {
        //Cryo Reactors: {cryoReactorsCount}
        //Heat Reactors: {heatReactorsCount}
        //Energy Modules: {energyModulesCount}
        //Absorbing Modules: {absorbingModulesCount}
        //Total Energy Output: {totalEnergyOutput}
        //Total Heat Absorbing: {totalHeatAbsorbing}
        StringBuilder sb = new StringBuilder();
        sb.append("Cryo Reactors: ").append(this.cryoReactorsCount).append(System.lineSeparator());
        sb.append("Heat Reactors: ").append(this.heatReactorsCount).append(System.lineSeparator());
        sb.append("Energy Modules: ").append(this.energyModuleCount).append(System.lineSeparator());
        sb.append("Absorbing Modules: ").append(this.absorbingModulesCount).append(System.lineSeparator());

        long totalEnergyOutput = this.idReactor.values().stream().mapToLong(r -> r.getTotalEnergyOutput()).sum();
        //TODO should some of these be 0, because of some of the getter Logic?
        long totalHeatAbsorbing = this.idReactor.values().stream().mapToLong(r -> r.getTotalHeatAbsorbing()).sum();

        sb.append("Total Energy Output: ").append(totalEnergyOutput).append(System.lineSeparator());
        sb.append("Total Heat Absorbing: ").append(totalHeatAbsorbing);

        return sb.toString();
    }

    private void incrementId() {
        this.idCounter++;
    }

    private void incrementEnergyModuleCount() {
        this.energyModuleCount++;
    }

    private void incrementAbsorbingModulesCount() {
        this.absorbingModulesCount++;
    }

    private void incrementCryoReactorsCount() {
        this.cryoReactorsCount++;
    }

    private void incrementHeatReactorsCount() {
        this.heatReactorsCount++;
    }
}
