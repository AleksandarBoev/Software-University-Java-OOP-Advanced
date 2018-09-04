package callofduty.engine;

import callofduty.interfaces.Engine;
import callofduty.interfaces.InputReader;
import callofduty.interfaces.MissionManager;
import callofduty.interfaces.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MissionEngine implements Engine {
    private InputReader reader;
    private OutputWriter writer;
    private MissionManager manager;

    public MissionEngine(InputReader reader, OutputWriter writer, MissionManager manager) {
        this.reader = reader;
        this.writer = writer;
        this.manager = manager;
    }

    @Override
    public void run() {
        String input;
        while (!"Over".equals(input = this.reader.readLine())) {
            List<String> arguments = new ArrayList<>(Arrays.asList(input.split(" ")));
            String command = arguments.get(0);

            switch (command) {
                case "Agent":
                    this.writer.println(this.manager.agent(arguments));
                    break;

                case "Request":
                    this.writer.println(this.manager.request(arguments));
                    break;

                case "Complete":
                    this.writer.println(this.manager.complete(arguments));
                    break;

                case "Status":
                    this.writer.println(this.manager.status(arguments));
                    break;
            }
        }

        this.writer.println(this.manager.over(new ArrayList<>()));
    }
}
