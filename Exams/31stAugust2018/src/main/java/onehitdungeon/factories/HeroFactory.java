package onehitdungeon.factories;

import onehitdungeon.heroes.MageHero;
import onehitdungeon.heroes.PaladinHero;
import onehitdungeon.interfaces.Hero;

import java.util.List;

public class HeroFactory implements Factory<Hero> {
    //•	Hero {type} {name}
    @Override
    public Hero create(List<String> arguments) {
        String heroType = arguments.get(1);
        String heroName = arguments.get(2);

        switch (heroType) {
            case "Paladin":
                return new PaladinHero(heroName);

            case "Mage":
                return new MageHero(heroName);
        }
        return null;

    }
}
