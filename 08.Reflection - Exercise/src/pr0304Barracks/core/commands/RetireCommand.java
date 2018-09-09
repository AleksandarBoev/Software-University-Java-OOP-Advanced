package pr0304Barracks.core.commands;

import jdk.jshell.spi.ExecutionControl;
import pr0304Barracks.annotations.Inject;
import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.UnitFactory;

public class RetireCommand extends Command {
    @Inject
    private Repository repository;

    @Inject
    private UnitFactory unitFactory;

    public RetireCommand(String[] data) {
        super(data);
    }

//    public RetireCommand(String[] data, Repository repository, UnitFactory unitFactory) {
//        super(data, repository, unitFactory);
//    }

    @Override
    public String execute() throws ExecutionControl.NotImplementedException {
        return this.repository.removeUnit(super.getData()[0]);
    }
}
