package p04_cardToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        CardRanks cardRank = CardRanks.valueOf(CardRanks.class, reader.readLine());
        CardSuits cardSuit = CardSuits.valueOf(CardSuits.class, reader.readLine());
        reader.close();

        Card card = new Card(cardSuit, cardRank);
        System.out.println(card);
    }
}
