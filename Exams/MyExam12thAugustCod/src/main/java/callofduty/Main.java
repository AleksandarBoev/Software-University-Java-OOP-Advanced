package callofduty;

import callofduty.domain.engine.MissionManagerImpl;
import callofduty.domain.io.ConsoleReader;
import callofduty.domain.io.ConsoleWriter;
import callofduty.interfaces.InputReader;
import callofduty.interfaces.MissionManager;
import callofduty.interfaces.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        InputReader reader = new ConsoleReader();
        OutputWriter writer = new ConsoleWriter();

        MissionManager missionManager = new MissionManagerImpl();

        String input;
        while (!"Over".equals(input = reader.readLine())) {
            List<String> arguments = new ArrayList<>(Arrays.asList(input.split(" ")));

            String command = arguments.get(0);
            switch (command) {
                case "Agent":
                    writer.println(missionManager.agent(arguments));
                    break;

                case "Request":
                    writer.println(missionManager.request(arguments));
                    break;

                case "Status":
                    writer.println(missionManager.status(arguments));
                    break;

                case "Complete":
                    writer.println(missionManager.complete(arguments));
                    break;
            }
        }

        writer.println(missionManager.over(new ArrayList<>()));
    }
}




