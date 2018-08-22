package panzer.entities.engine;

import panzer.contracts.*;
import panzer.entities.manager.ManagerImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PanzerEngine implements Engine {
    InputReader reader;
    OutputWriter writer;
    Manager manager;

    public PanzerEngine(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
        this.manager = new ManagerImpl();
    }

    @Override
    public void run() {
        String input;
        while (!"Terminate".equals(input = this.reader.readLine().trim())) {
            List<String> arguments = new ArrayList<>(Arrays.asList(input.split(" ")));
            String command = arguments.get(0);

            switch (command) {
                case "Vehicle":
                    this.writer.println(this.manager.addVehicle(arguments));
                    break;

                case "Part":
                    this.writer.println(this.manager.addPart(arguments));
                    break;

                case "Inspect":
                    this.writer.println(this.manager.inspect(arguments));
                    break;

                case "Battle":
                    this.writer.println(this.manager.battle(arguments));
                    break;
            }
        }

        this.writer.println(this.manager.terminate(new ArrayList<>()));
    }
}
