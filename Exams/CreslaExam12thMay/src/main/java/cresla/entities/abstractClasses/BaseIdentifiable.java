package cresla.entities.abstractClasses;

import cresla.interfaces.Identifiable;

public abstract class BaseIdentifiable implements Identifiable {
    private int id;
//    private int additionalParam; //do it here or for every other class?

    protected BaseIdentifiable(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
