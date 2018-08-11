package cresla.entities.abstractClasses;

import cresla.interfaces.EnergyModule;

public abstract class BaseEnergyModel extends BaseModule implements EnergyModule {
    private int energyOutput;

    protected BaseEnergyModel(int id, int energyOutput) {
        super(id);
        this.energyOutput = energyOutput;
    }

    @Override
    public int getEnergyOutput() {
        return this.energyOutput;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("%s: %d", "Energy Output", this.getEnergyOutput()));

        return sb.toString();
    }

}
