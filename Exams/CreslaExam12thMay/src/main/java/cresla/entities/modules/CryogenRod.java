package cresla.entities.modules;

import cresla.entities.abstractClasses.BaseModule;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Module;

public class CryogenRod extends BaseModule implements EnergyModule {
    private int energyOutput;

    public CryogenRod(int id, int energyOutput) {
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
        sb.append(String.format("%s Module - %d%n", this.getClass().getSimpleName(), this.getId()));
        sb.append(String.format("%s: %d", "Energy Output", this.energyOutput));

        return sb.toString();
    }
}
