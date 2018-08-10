package hell.engine;

import hell.factories.Factory;
import hell.interfaces.*;
import hell.repository.Repository;

import java.io.IOException;

public class EngineImpl implements Engine {
    private InputReader reader;
    private OutputWriter writer;
    private Factory<Item> itemFactory;
    private Factory<Hero> heroFactory;
    private Factory<Recipe> recipeFactory;
    private Repository<Hero> heroRepository;

    public EngineImpl(InputReader reader,
                      OutputWriter writer,
                      Factory<Item> itemFactory,
                      Factory<Hero> heroFactory,
                      Factory<Recipe> recipeFactory,
                      Repository<Hero> heroRepository) {
        this.reader = reader;
        this.writer = writer;
        this.itemFactory = itemFactory;
        this.heroFactory = heroFactory;
        this.recipeFactory = recipeFactory;
        this.heroRepository = heroRepository;
    }

    public void run() throws IOException, NoSuchFieldException, IllegalAccessException {
        String input;
        while (!"Quit".equals(input = this.reader.readLine())) {
            String[] tokens = input.split(" ");
            String command = tokens[0];

            switch (command) {
                case "Hero":
                    Hero createdHero = this.heroFactory.create(tokens);
                    this.writer.writeLine("Created %s - %s", createdHero.getClass().getSimpleName(), createdHero.getName());
                    this.heroRepository.put(createdHero);
                    break;

                case "Item":
                    Item createdItem = this.itemFactory.create(tokens);
                    String heroName = tokens[2];
                    Hero currentHero = this.heroRepository.get(heroName);
                    currentHero.addItem(createdItem);
                    this.writer.writeLine("Added item - %s to Hero - %s", createdItem.getName(), heroName);
                    break;

                case "Recipe":
                    Recipe createdRecipe = this.recipeFactory.create(tokens);
                    heroName = tokens[2];
                    currentHero = this.heroRepository.get(heroName);
                    currentHero.addRecipe(createdRecipe);
                    this.writer.writeLine("Added recipe - %s to Hero - %s", createdRecipe.getName(), heroName);
                    break;

                case "Inspect":
                    currentHero = this.heroRepository.get(tokens[1]);
                    this.writer.writeLine(currentHero.toString());
                    break;
            }
        }

        this.heroRepository.printAll(this.writer);
    }
}
