package cresla.entities.abstractClasses;

import cresla.interfaces.AbsorbingModule;

public abstract class BaseAbsorbingModule extends BaseModule implements AbsorbingModule {
    private int heatAbsorbing;

    protected BaseAbsorbingModule(int id, int heatAbsorbing) {
        super(id);
        this.heatAbsorbing = heatAbsorbing;
    }

    @Override
    public int getHeatAbsorbing() {
        return this.heatAbsorbing;
    }

    //TODO toString

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s Module - %d%n", this.getClass().getSimpleName(), this.getId()));
        sb.append(String.format("%s: %d", "Heat Absorbing", this.heatAbsorbing));

        return sb.toString();
    }
}
