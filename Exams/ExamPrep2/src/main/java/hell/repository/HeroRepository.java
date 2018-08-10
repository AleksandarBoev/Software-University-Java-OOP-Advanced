package hell.repository;

import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.OutputWriter;

import java.util.*;
import java.util.stream.Collectors;

public class HeroRepository implements Repository<Hero> {
    private Map<String, Hero> nameHero;
    private Comparator<Hero> heroComparator;

    public HeroRepository() {
        this.nameHero = new LinkedHashMap<>();
        this.heroComparator = new HeroComparator();
    }

    @Override
    public void put(Hero hero) {
        this.nameHero.put(hero.getName(), hero);
    }

    @Override
    public Hero get(String name) {
        return this.nameHero.get(name);
    }

    @Override
    public void printAll(OutputWriter outputWriter) throws NoSuchFieldException, IllegalAccessException {
        Set<Hero> sortedHeroes = new TreeSet<>(this.heroComparator); //there's probably a better way of doing this
        for (Hero hero : nameHero.values()) {
            sortedHeroes.add(hero);
        }

        int count = 1;
        for (Hero hero : sortedHeroes) {
            outputWriter.writeLine(this.heroToString(count++, hero));
        }
    }

    private String heroToString(int number, Hero hero) throws NoSuchFieldException, IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d. %s: %s", number, hero.getClass().getSimpleName(), hero.getName())).append(System.lineSeparator());
        sb.append("###HitPoints: ").append(hero.getHitPoints()).append(System.lineSeparator());
        sb.append("###Damage: ").append(hero.getDamage()).append(System.lineSeparator());
        sb.append("###Strength: ").append(hero.getStrength()).append(System.lineSeparator());
        sb.append("###Agility: ").append(hero.getAgility()).append(System.lineSeparator());
        sb.append("###Intelligence: ").append(hero.getIntelligence()).append(System.lineSeparator());

        Collection<Item> items = hero.getItems();
        String itemNames = "";
        if (items.isEmpty())
            itemNames = "None";
        else
            itemNames = String.join(", ", items.stream().map(i -> i.getName()).collect(Collectors.toList()));

        sb.append("###Items: ").append(itemNames);

        return sb.toString();
    }

    //1. Barbarian: Ivan
    //###HitPoints: 450
    //###Damage: 200
    //###Strength: 115
    //###Agility: 35
    //###Intelligence: 20
    //###Items: Spear
    //2. Assassin: Pesho
    //###HitPoints: 150
    //###Damage: 300
    //###Strength: 25
    //###Agility: 100
    //###Intelligence: 15
    //###Items: None


}
