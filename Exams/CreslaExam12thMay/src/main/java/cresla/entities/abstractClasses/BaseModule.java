package cresla.entities.abstractClasses;

import cresla.interfaces.Module;

public abstract class BaseModule implements Module {
    private int id;

    protected BaseModule(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.format("%s Module - %d%n", this.getClass().getSimpleName(), this.getId());
    }
}
