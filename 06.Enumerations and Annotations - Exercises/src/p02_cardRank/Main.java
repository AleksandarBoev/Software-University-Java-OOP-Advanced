package p02_cardRank;

public class Main {
    public static void main(String[] args) {
        CardRanks[] cardBanks = CardRanks.values();

        System.out.println("Card Ranks:");
        for (CardRanks cardSuit : cardBanks) {
            System.out.println(cardSuit);
        }
    }
}
