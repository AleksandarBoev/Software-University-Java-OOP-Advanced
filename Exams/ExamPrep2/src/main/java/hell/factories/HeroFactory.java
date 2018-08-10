package hell.factories;

import hell.entities.heroes.Assassin;
import hell.entities.heroes.Barbarian;
import hell.entities.heroes.Wizard;
import hell.entities.miscellaneous.HeroInventory;
import hell.interfaces.Hero;

public class HeroFactory implements Factory<Hero> {
    @Override
    public Hero create(String[] data) { //TODO fix
        String heroName = data[1];
        switch (data[2]) {
            case "Barbarian":
                return new Barbarian(heroName, new HeroInventory()); //acceptable way i guess

            case "Assassin":
                return new Assassin(heroName, new HeroInventory());

            case "Wizard":
                return new Wizard(heroName, new HeroInventory());
        }

        return null;
    }
}
