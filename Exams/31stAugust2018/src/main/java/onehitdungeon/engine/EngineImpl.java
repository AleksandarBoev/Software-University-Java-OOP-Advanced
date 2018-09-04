package onehitdungeon.engine;

import onehitdungeon.interfaces.DungeonManager;
import onehitdungeon.interfaces.InputReader;
import onehitdungeon.interfaces.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EngineImpl implements Engine {
    private InputReader reader;
    private OutputWriter writer;
    private DungeonManager manager;

    public EngineImpl(InputReader reader, OutputWriter writer, DungeonManager manager) {
        this.reader = reader;
        this.writer = writer;
        this.manager = manager;
    }

    @Override
    public void run() {
        String input;
        while (!"Quit".equals(input = this.reader.readLine())) {
            List<String> arguments = Arrays.asList(input.split(" "));

            String command = arguments.get(0);

            switch (command) {
                case "Hero":
                    this.writer.println(this.manager.hero(arguments));
                    break;

                case "Select":
                    this.writer.println(this.manager.select(arguments));
                    break;

                case "Status":
                    this.writer.println(this.manager.status(arguments));
                    break;

                case "Fight":
                    this.writer.println(this.manager.fight(arguments));
                    break;

                case "Advance":
                    this.writer.println(this.manager.advance(arguments));
                    break;

                case "Train":
                    this.writer.println(this.manager.train(arguments));
                    break;
            }
        } //Over command has been written
        this.writer.println(this.manager.quit(new ArrayList<>()));

    }
}
