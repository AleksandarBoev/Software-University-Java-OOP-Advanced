package p05_cardCompareTo;

public class Card implements Comparable<Card> {
    private CardSuits cardSuit;
    private CardRanks cardRank;

    public Card(CardSuits cardSuit, CardRanks cardRank) {
        this.cardSuit = cardSuit;
        this.cardRank = cardRank;
    }

    @Override
    public String toString() {
        return String.format("Card name: %s of %s; Card power: %d", this.cardRank.name(), this.cardSuit.name(), this.calculatePower());
    }

    private int calculatePower() {
        return this.cardSuit.getPower() + this.cardRank.getPower();
    }


    @Override
    public int compareTo(Card anotherCard) {
        return Integer.compare(this.calculatePower(), anotherCard.calculatePower());
    }
}
