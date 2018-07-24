package p07_deckOfCards;

public class Card {
    private CardSuits cardSuit;
    private CardRanks cardRank;

    public Card(CardSuits cardSuit, CardRanks cardRank) {
        this.cardSuit = cardSuit;
        this.cardRank = cardRank;
    }

    @Override
    public String toString() {
        return String.format("%s of %s", this.cardRank.name(), this.cardSuit.name());
    }
}
