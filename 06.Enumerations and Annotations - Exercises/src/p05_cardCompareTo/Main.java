package p05_cardCompareTo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        CardRanks cardRank1 = CardRanks.valueOf(CardRanks.class, reader.readLine());
        CardSuits cardSuit1 = CardSuits.valueOf(CardSuits.class, reader.readLine());

        CardRanks cardRank2 = CardRanks.valueOf(CardRanks.class, reader.readLine());
        CardSuits cardSuit2 = CardSuits.valueOf(CardSuits.class, reader.readLine());
        reader.close();

        Card card1 = new Card(cardSuit1, cardRank1);
        Card card2 = new Card(cardSuit2, cardRank2);

        if (card1.compareTo(card2) >= 0) {
            System.out.println(card1);
        } else {
            System.out.println(card2);
        }
    }
}
