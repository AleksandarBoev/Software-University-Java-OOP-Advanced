package onehitdungeon;

import onehitdungeon.core.HeroTrainerImpl;
import onehitdungeon.engine.Engine;
import onehitdungeon.engine.EngineImpl;
import onehitdungeon.factories.Factory;
import onehitdungeon.factories.HeroFactory;
import onehitdungeon.interfaces.*;
import onehitdungeon.io.ConsoleReader;
import onehitdungeon.io.ConsoleWriter;
import onehitdungeon.manager.DungeonManagerImpl;

public class Main {
    public static void main(String[] args) {
        InputReader reader = new ConsoleReader();
        OutputWriter writer = new ConsoleWriter();

        Factory<Hero> heroFactory = new HeroFactory();
        HeroTrainer heroTrainer = new HeroTrainerImpl();
        DungeonManager manager = new DungeonManagerImpl(heroFactory, heroTrainer);

        Engine engine = new EngineImpl(reader, writer, manager);
        engine.run();
    }
}
