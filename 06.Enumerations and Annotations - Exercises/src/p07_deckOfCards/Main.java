package p07_deckOfCards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        reader.close();

        Card[] cards = new Card[52];
        CardRanks[] cardRanks = CardRanks.values();
        CardSuits[] cardSuits = CardSuits.values();

        int counter = 0;
        for (int i = 0; i < cardSuits.length; i++) {
            for (int j = 0; j < cardRanks.length; j++) {
                Card currentCard = new Card(cardSuits[i], cardRanks[j]);
                cards[counter++] = currentCard;
            }
        }

        for (int i = 0; i < cards.length; i++) {
            System.out.println(cards[i]);
        }
    }
}
