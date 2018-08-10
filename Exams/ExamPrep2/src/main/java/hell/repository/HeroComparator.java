package hell.repository;

import hell.interfaces.Hero;

import java.util.Comparator;

public class HeroComparator implements Comparator<Hero> {
    @Override
    public int compare(Hero hero1, Hero hero2) {
        long hero1Stats = hero1.getStrength() + hero1.getAgility() + hero1.getIntelligence();
        long hero2Stats = hero2.getStrength() + hero2.getAgility() + hero2.getIntelligence();

        int comparisonResult = Long.compare(hero2Stats, hero1Stats);

        if (comparisonResult == 0) {
            hero1Stats = hero1.getHitPoints() + hero1.getDamage();
            hero2Stats = hero2.getHitPoints() + hero2.getDamage();
            comparisonResult = Long.compare(hero2Stats, hero1Stats);
        }

        return comparisonResult;
    }
}
