package pr0304Barracks.core.commands;

import jdk.jshell.spi.ExecutionControl;
import pr0304Barracks.annotations.Inject;
import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.Unit;
import pr0304Barracks.contracts.UnitFactory;

public class AddCommand extends Command {
    @Inject
    private Repository repository;

    @Inject
    private UnitFactory unitFactory;

    public AddCommand(String[] data) {
        super(data);
    }
//    public AddCommand(String[] data, Repository repository, UnitFactory unitFactory) { // could be different implementations of Repository or UnitFactory
//        super(data, repository, unitFactory);  //but which implementation to be used is not decided by THIS class. This is a form of Dependency Injection
//    }

    @Override
    public String execute() throws ExecutionControl.NotImplementedException {
//        Unit newlyCreatedUnit = super.getUnitFactory().createUnit(this.getData()[0]);
        Unit newlyCreatedUnit = this.unitFactory.createUnit(super.getData()[0]);
//        super.getRepository().addUnit(newlyCreatedUnit);
        this.repository.addUnit(newlyCreatedUnit);
        return String.format("%s added!", this.getData()[0]);
    }
}
