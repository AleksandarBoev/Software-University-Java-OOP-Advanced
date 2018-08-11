package cresla.entities.reactors;

import cresla.entities.abstractClasses.BaseReactor;

public class HeatReactor extends BaseReactor {
    private int heatReductionIndex;

    public HeatReactor(int id, int moduleCount, int heatReductionIndex) {
        super(id, moduleCount);
        this.heatReductionIndex = heatReductionIndex;
    }

    @Override
    public long getTotalEnergyOutput() {
        long totalEnergyOutput = super.getModuleContainer().getTotalEnergyOutput();

        if (totalEnergyOutput > this.getTotalHeatAbsorbing())
            return 0;
        else
            return totalEnergyOutput;
    }

    @Override
    public long getTotalHeatAbsorbing() {
        //If the Reactor is a HeatReactor, you must add the heatReduction to the heat absorbing.
        long heatAbsorbing = super.getModuleContainer().getTotalHeatAbsorbing();
        long actualHeatAbsorbing = heatAbsorbing + this.heatReductionIndex;

        return actualHeatAbsorbing;
    }
}
