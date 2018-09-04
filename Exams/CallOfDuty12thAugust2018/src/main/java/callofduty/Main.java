package callofduty;

import callofduty.engine.MissionEngine;
import callofduty.interfaces.Engine;
import callofduty.interfaces.InputReader;
import callofduty.interfaces.MissionManager;
import callofduty.interfaces.OutputWriter;
import callofduty.io.ConsoleReader;
import callofduty.io.ConsoleWriter;
import callofduty.manager.MissionManagerImpl;

public class Main {
    public static void main(String[] args)  {
        InputReader consoleReader = new ConsoleReader();
        OutputWriter consoleWriter = new ConsoleWriter();
        MissionManager missionManager = new MissionManagerImpl();

        Engine missionEngine = new MissionEngine(consoleReader, consoleWriter, missionManager);
        missionEngine.run();
    }
}




