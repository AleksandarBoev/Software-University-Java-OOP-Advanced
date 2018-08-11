package cresla.entities.abstractClasses;

import cresla.interfaces.Module;

public abstract class BaseModule extends BaseIdentifiable implements Module {
    protected BaseModule(int id) {
        super(id);
    }
}
