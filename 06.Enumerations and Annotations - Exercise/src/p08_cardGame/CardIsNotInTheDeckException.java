package p08_cardGame;

public class CardIsNotInTheDeckException extends Error { //extending error because it is an "unchecked" kind of throwable
    private static final String CARD_IS_NOT_IN_THE_DECK_MESSAGE = "Card is not in the deck.";

    public CardIsNotInTheDeckException() {
        super(CARD_IS_NOT_IN_THE_DECK_MESSAGE);
    }
}
