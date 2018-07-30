package pr0304Barracks.core.commands;

import jdk.jshell.spi.ExecutionControl;
import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.Unit;
import pr0304Barracks.contracts.UnitFactory;

public class AddCommand extends Command { //adds a new Unit to the repository, using the unitFactory
    public AddCommand(String[] data, Repository repository, UnitFactory unitFactory) { // could be different implementations of Repository or UnitFactory
        super(data, repository, unitFactory);  //but which implementation to be used is not decided by THIS class. This is a form of Dependency Injection
    }

    @Override
    public String execute() throws ExecutionControl.NotImplementedException {
        //data will have only pikeman/gunner/archer/horseman
        Unit newlyCreatedUnit = super.getUnitFactory().createUnit(this.getData()[0]);

        super.getRepository().addUnit(newlyCreatedUnit);

        return String.format("%s added!", this.getData()[0]);
    }
}
