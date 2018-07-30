package pr0304Barracks.core.commands;

import pr0304Barracks.contracts.CommandInterpreter;
import pr0304Barracks.contracts.Executable;
import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.UnitFactory;

import java.util.Arrays;

public class CommandInterpreterImpl implements CommandInterpreter {
    private Repository repository;
    private UnitFactory unitFactory;
    private String[] data;

    public CommandInterpreterImpl(Repository repository, UnitFactory unitFactory, String[] data) {
        this.repository = repository;
        this.unitFactory = unitFactory;
        this.data = data;
    }

    @Override
    public Executable interpretCommand() {
        String commandName = this.data[0];
        String[] updatedData = Arrays.stream(this.data).skip(1).toArray(n -> new String[n]);
        //skipping the command String and leaving only the necessary data for the commands below

        switch (commandName.toLowerCase()) {
            case "add":
                return new AddCommand(updatedData, this.repository, this.unitFactory);

            case "report":
                return new ReportCommand(updatedData, this.repository, this.unitFactory);

            case "retire":
                return new RetireCommand(updatedData, this.repository, this.unitFactory);

            default:
                return null;
        }
    }
}
