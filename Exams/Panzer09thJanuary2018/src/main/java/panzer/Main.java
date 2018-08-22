package panzer;

import panzer.contracts.Engine;
import panzer.contracts.InputReader;
import panzer.contracts.OutputWriter;
import panzer.entities.engine.PanzerEngine;
import panzer.entities.io.ConsoleReader;
import panzer.entities.io.ConsoleWriter;

public class Main {
    public static void main(String[] args) {
        InputReader reader = new ConsoleReader();
        OutputWriter writer = new ConsoleWriter();
        Engine panzerEngine = new PanzerEngine(reader, writer);
        panzerEngine.run();
    }
}
