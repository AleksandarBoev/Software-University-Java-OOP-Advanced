package p08_cardGame;

public class CardDoesNotExistException extends Error {
    private static final String NO_SUCH_CARD_EXISTS_MESSAGE = "No such card exists.";

    public CardDoesNotExistException() {
        super(NO_SUCH_CARD_EXISTS_MESSAGE);
    }
}
