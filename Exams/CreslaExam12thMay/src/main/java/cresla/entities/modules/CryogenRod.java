package cresla.entities.modules;

import cresla.entities.abstractClasses.BaseEnergyModel;
import cresla.entities.abstractClasses.BaseModule;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Module;

public class CryogenRod extends BaseEnergyModel {
    public CryogenRod(int id, int energyOutput) {
        super(id, energyOutput);
    }
}
