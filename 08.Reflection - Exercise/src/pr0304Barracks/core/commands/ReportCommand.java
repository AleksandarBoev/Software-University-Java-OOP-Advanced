package pr0304Barracks.core.commands;

import pr0304Barracks.annotations.Inject;
import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.UnitFactory;

public class ReportCommand extends Command {
    @Inject
    private Repository repository;

    @Inject
    private UnitFactory unitFactory;

//    public ReportCommand(String[] data, Repository repository, UnitFactory unitFactory) {
    //        super(data, repository, unitFactory);
    //    }


    public ReportCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        return this.repository.getStatistics();
    }

}
