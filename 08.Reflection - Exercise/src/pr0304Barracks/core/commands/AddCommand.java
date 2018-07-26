package pr0304Barracks.core.commands;

import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.UnitFactory;

public class AddCommand extends Command {
    public AddCommand(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
//        return super.getRepository().addUnit();
        return null; //TODO problem. So, different methods require different parameters, but there is just one execute command. What whould I do?
    }
}
