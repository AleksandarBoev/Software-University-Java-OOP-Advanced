package cresla;

import cresla.entities.commandManager.ManagerImpl;
import cresla.entities.io.ConsoleReader;
import cresla.entities.io.ConsoleWriter;
import cresla.interfaces.InputReader;
import cresla.interfaces.Manager;
import cresla.interfaces.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InputReader consoleReader = new ConsoleReader();
        OutputWriter consoleWriter = new ConsoleWriter();
        Manager manager = new ManagerImpl();

        String input;
        while (!"Exit".equals(input = consoleReader.readLine())) {
            List<String> arguments = new ArrayList<>(Arrays.asList(input.split(" ")));

            String output = "";
            String command = arguments.get(0);
            switch (command) {
                case "Reactor":
                    output = manager.reactorCommand(arguments);
                    break;

                case "Module":
                    output = manager.moduleCommand(arguments);
                    break;

                case "Report":
                    output = manager.reportCommand(arguments);
                    break;
            }

            consoleWriter.writeLine(output);
        }

        consoleWriter.writeLine(manager.exitCommand(new ArrayList<>()));
    }
}
