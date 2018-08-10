package hell;

import hell.engine.Engine;
import hell.engine.EngineImpl;
import hell.factories.CommonItemFactory;
import hell.factories.HeroFactory;
import hell.factories.RecipeItemFactory;
import hell.interfaces.OutputWriter;
import hell.io.ConsoleInputReader;
import hell.io.ConsoleOutputWriter;
import hell.repository.HeroRepository;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
        Engine engine = new EngineImpl(
                new ConsoleInputReader(),
                new ConsoleOutputWriter(),
                new CommonItemFactory(),
                new HeroFactory(),
                new RecipeItemFactory(),
                new HeroRepository());

        engine.run();
    }
}