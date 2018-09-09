package pr0304Barracks.core.commands;

/*
    Code that has been commented works. These are just different versions of the program,
    depending on the task.
 */

import pr0304Barracks.annotations.Inject;
import pr0304Barracks.contracts.CommandInterpreter;
import pr0304Barracks.contracts.Executable;
import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

public class CommandInterpreterImpl implements CommandInterpreter {
    private static final String COMMAND_PATH = "pr0304Barracks.core.commands.";

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

        try {
            Class commandClass = Class.forName(COMMAND_PATH +
                    Character.toUpperCase(commandName.charAt(0)) + //add --> AddCommand
                    commandName.substring(1) +
                    "Command");
//            Constructor commandConstructor = commandClass.getConstructor(String[].class, Repository.class, UnitFactory.class);
//            return (Executable)commandConstructor.newInstance(updatedData, this.repository, this.unitFactory);
            //The code above works. Now it will get refactored for Problem 5.

            Constructor commandConstructorWithoutDependencies = commandClass.getConstructor(String[].class);
            Executable newlyCreatedCommand = (Executable) commandConstructorWithoutDependencies.newInstance((Object)updatedData);

            Field[] fieldsToBeInjected = Arrays.stream(commandClass.getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(Inject.class))
                    .toArray(Field[]::new);

            Field[] dependencies = this.getClass().getDeclaredFields(); //get the fields of THIS class (CommandInterpreterImpl)

            for (Field field : fieldsToBeInjected) {
                field.setAccessible(true);

                for (Field dependency : dependencies) {
                    if (dependency.getType().getTypeName().equals(field.getType().getTypeName())) {
                        field.set(newlyCreatedCommand, dependency.get(this));
                    }
                }

                field.setAccessible(false);
            }

            return newlyCreatedCommand;
        } catch (Exception e) {
            return null;
        }
//        switch (commandName.toLowerCase()) {
//            case "add":
//                return new AddCommand(updatedData, this.repository, this.unitFactory);
//
//            case "report":
//                return new ReportCommand(updatedData, this.repository, this.unitFactory);
//
//            case "retire":
//                return new RetireCommand(updatedData, this.repository, this.unitFactory);
//
//            default:
//                return null;
//        }
    }
}
