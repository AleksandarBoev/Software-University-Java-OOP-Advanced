package onehitdungeon.manager;

import onehitdungeon.factories.Factory;
import onehitdungeon.interfaces.DungeonManager;
import onehitdungeon.interfaces.Hero;
import onehitdungeon.interfaces.HeroTrainer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DungeonManagerImpl implements DungeonManager {
    private Hero currentHero; //TODO if some tests give runtime errors, maybe I should write code in case there is no current hero
    private Map<String, Hero> nameHero;
    private Factory<Hero> heroFactory;
    private HeroTrainer heroTrainer;

    private int dungeonLevel;
    private int dungeonPower;
    private double dungeonReward;

    public DungeonManagerImpl(Factory<Hero> heroFactory, HeroTrainer heroTrainer) {
        this.currentHero = null;
        this.nameHero = new LinkedHashMap<>();
        this.heroFactory = heroFactory;
        this.heroTrainer = heroTrainer;

        this.dungeonLevel = 1; //would be better with constants
        this.dungeonPower = 20;
        this.dungeonReward = 15.0;
    }

    //all commands:
// •Hero {type} {name}
//•	Select {name}
//•	Status
//•	Fight
//•	Advance
//•	Train
//•	Over


    //•	Hero Command – registers a Hero of the given type with given name. Prints the following result:
    //Successfully added {heroType} – {name}.
    @Override
    public String hero(List<String> arguments) {
        String heroName = arguments.get(2);
        Hero newHero = this.heroFactory.create(arguments);
        this.nameHero.put(heroName, newHero);

        if (this.currentHero == null) {
            this.currentHero = newHero;
        }
        return String.format("Successfully added %s - %s.",
                newHero.getHeroClass().replace("Hero", ""), heroName);
    }

    //•	Select Command – selects the Hero, with the given name, as the currently selected hero.
    //Prints the following result:
    //Successfully selected {heroType} - {heroName}.
    @Override
    public String select(List<String> arguments) {
        String heroName = arguments.get(1);
        this.currentHero = this.nameHero.get(heroName);
        String heroType = this.currentHero.getHeroClass().replace("Hero", "");
        return String.format("Successfully selected %s - %s.",
                heroType, heroName);
    }

    //•	Status command – provides detailed information about the currently selected hero, in the following format:
    @Override
    public String status(List<String> arguments) {
        return this.currentHero.toString();
    }

    //•	Fight Command – makes the currently selected hero fight the current dungeon level.
    //o	Prints the following result if the battle is won:
    //Fight won. You’ve gained {goldEarned} gold.
    //o	Prints the following result if the battle is NOT won:
    //Fight lost. You’ve returned to the previous level.
    @Override
    public String fight(List<String> arguments) {
//        int dungeonPower = this.calculateDungeonPower();
        int heroPower = this.currentHero.getTotalBattlePower();

        if (heroPower >= this.dungeonPower) {//TODO possible problem
//            double dungeonReward = this.calculateDungeonGoldGiven();
            this.currentHero.earnGold(this.dungeonReward);
            return String.format("Fight won. You've gained %.2f gold.", this.dungeonReward);
        } else {
            if (this.dungeonLevel > 1) {
                this.dungeonLevel--;
                this.dungeonPower /= 2;
                this.dungeonReward /= 2.0;
            }
            return "Fight lost. You've returned to the previous level.";
        }
    }

    //•	Advance Command – advances the current dungeon level.
    //Successfully advanced to dungeon level {newDungeonLevel}.
    @Override
    public String advance(List<String> arguments) {
        this.dungeonLevel++;
        this.dungeonReward *= 2.0;
        this.dungeonPower *= 2;
        return String.format("Successfully advanced to dungeon level %d.", this.dungeonLevel);
    }

    //•	Train Command – trains the currently selected hero.
    //o	Prints the following result if the Hero has enough gold and successfully trains:
    //Successfully trained hero. Current total battle power: {upgradedTotalBattlePower}.
    //o	Prints the following result if the Hero does NOT have enough gold and does NOT train:
    //Insufficient gold for training. Needed {upgradeCost}. Got {currentGold}.
    @Override
    public String train(List<String> arguments) {
        Double priceForTraining = this.currentHero.getTotalPriceForUpgrade();
        Double goldAvaliable = this.currentHero.getGold();

        //TODO hero must pay the gold to train!
        if (goldAvaliable >= priceForTraining) {
            this.currentHero.payGold(this.currentHero.getTotalPriceForUpgrade());
            this.heroTrainer.trainHero(this.currentHero);
            return String.format("Successfully trained hero. Current total battle power: %d.",
                    this.currentHero.getTotalBattlePower());
        } else {
            return String.format("Insufficient gold for training. Needed %.2f. Got %.2f.",
                    priceForTraining, goldAvaliable);
        }
    }

    //•	Quit command – Terminates the program; prints detailed statistics about the whole system. The format, in which the statistics should be printed is:
    //{hero1type} {hero1name} – {hero1totalBattlePower} (BP)
    //{hero2type} {hero2name} – {hero2totalBattlePower} (BP)
    //{hero3type} {hero3name} – {hero3totalBattlePower} (BP)
    //. . .
    //####################
    //Dungeon level reached: {dungeonLevelReached}
    //o	You should traverse all heroes and print each Hero in the format specified above.
    //o	You should print the dungeonLevelReached. The first level is 1. Each time you advance the level is incremented.
    //Note: All output floating-point numbers must be formatted to the 2nd digit after the decimal point.
    @Override
    public String quit(List<String> arguments) { //the hero getters provide all the information that is needed!
        StringBuilder sb = new StringBuilder();
        for (Hero hero : this.nameHero.values()) {
            sb.append(String.format("%s %s - %d (BP)",
                    hero.getHeroClass().replace("Hero", ""), hero.getName(), hero.getTotalBattlePower()))
                    .append(System.lineSeparator());
        }
        sb.append("####################").append(System.lineSeparator());
        sb.append("Dungeon level reached: ").append(this.dungeonLevel);
        //{hero1type} {hero1name} – {hero1totalBattlePower} (BP)
        //{hero2type} {hero2name} – {hero2totalBattlePower} (BP)
        //{hero3type} {hero3name} – {hero3totalBattlePower} (BP)
        //. . .
        //####################
        //Dungeon level reached: {dungeonLevelReached} --> current level --> last 0 test confirms it
        return sb.toString();
    }

    private double calculateDungeonGoldGiven() {
        //15 -> 30 -> 60 -> 120 -> 240
        //1     2     3     4      5
        //1*15  2*15  4*15  8*15   16*15
        if (this.dungeonLevel == 1)
            return 15;
        else
            return this.dungeonLevel * 2.0 * 15;
    }

    //TODO test if the values are correct
    private int calculateDungeonPower() {
        //20 -> 40 -> 80 -> 160 -> 320
        //1     2     3      4      5
        //1*20  2*20  4*20  8*20   16*20
        return this.dungeonLevel * 2 * 20;
    }
}
