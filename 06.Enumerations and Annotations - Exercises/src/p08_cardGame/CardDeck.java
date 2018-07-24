package p08_cardGame;

import java.util.*;

public class CardDeck {
    private Set<String> possibleCards;
    private Map<String, Card> cardPack;

    public CardDeck() {
        setCardPack();
        this.possibleCards = new HashSet<>(cardPack.keySet());
    }

    public Card drawCard(String card) {
        if (!this.possibleCards.contains(card)) {
            throw new CardDoesNotExistException();
        } else if (!this.cardPack.containsKey(card)) {
            throw new CardIsNotInTheDeckException();
        } else {
            Card result = this.cardPack.get(card);
            this.cardPack.remove(card);
            return result;
        }
    }

    private void setCardPack() {
        this.cardPack = new HashMap<>(52);

        CardRanks[] cardRanks = CardRanks.values();
        CardSuits[] cardSuits = CardSuits.values();

        for (int i = 0; i < cardSuits.length; i++) {
            for (int j = 0; j < cardRanks.length; j++) {
                Card currentCard = new Card(cardSuits[i], cardRanks[j]);
                this.cardPack.put(currentCard.toString(), currentCard);
            }
        }
    }

}
