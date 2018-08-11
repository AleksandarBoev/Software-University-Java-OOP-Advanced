package cresla.entities.reactors;

import cresla.entities.abstractClasses.BaseReactor;
import cresla.interfaces.Container;

public class CryoReactor extends BaseReactor {
    private int cryoProductionIndex;

    public CryoReactor(int id, Container moduleContainer, int cryoProductionIndex) {
        super(id, moduleContainer);
        this.cryoProductionIndex = cryoProductionIndex;
    }

    @Override
    public long getTotalEnergyOutput() {
        //If the Reactor is a CryoReactor, you must multiply its energy output by the cryoProductionIndex.
        long energyOutput = super.getModuleContainer().getTotalEnergyOutput();
        long actualEnergyOutput = energyOutput * this.cryoProductionIndex;
        long totalHeatAbsorbing = this.getTotalHeatAbsorbing();

        if (actualEnergyOutput > totalHeatAbsorbing)
            return 0;
        else
            return actualEnergyOutput;
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return super.getModuleContainer().getTotalHeatAbsorbing();
    }
}
