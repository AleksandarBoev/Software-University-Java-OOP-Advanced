package p08_cardGame;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        CardDeck cardDeck = new CardDeck();

        String player1Name = reader.readLine();
        List<Card> player1Cards = new ArrayList<>();
        Pair<String, List<Card>> player1 = new Pair<>(player1Name, player1Cards);

        String player2Name = reader.readLine();
        List<Card> player2Cards = new ArrayList<>();
        Pair<String, List<Card>> player2 = new Pair<>(player2Name, player2Cards);

        while (player1.getValue().size() != 5) {
            String currentCard = reader.readLine();

            try {
                player1.getValue().add(cardDeck.drawCard(currentCard));
            } catch (CardDoesNotExistException cdnee) {
                System.out.println(cdnee.getMessage());
            } catch (CardIsNotInTheDeckException cinitde) {
                System.out.println(cinitde.getMessage());
            }
        }

        while (player2.getValue().size() != 5) {
            String currentCard = reader.readLine();

            try {
                player2.getValue().add(cardDeck.drawCard(currentCard));
            } catch (CardDoesNotExistException cdnee) {
                System.out.println(cdnee.getMessage());
            } catch (CardIsNotInTheDeckException cinitde) {
                System.out.println(cinitde.getMessage());
            }
        }
        reader.close();

        Optional<Card> highestValueCardPlayer1 = player1.getValue().stream()
                .sorted((c1, c2) -> c2.calculatePower().compareTo(c1.calculatePower()))
                .findFirst();

        Optional<Card> highestValueCardPlayer2 = player2.getValue().stream()
                .sorted((c1, c2) -> c2.calculatePower().compareTo(c1.calculatePower()))
                .findFirst();

        if (highestValueCardPlayer1.get().calculatePower() > highestValueCardPlayer2.get().calculatePower()) {
            System.out.printf("%s wins with %s.", player1.getKey(), highestValueCardPlayer1.get().toString());
        } else {
            System.out.printf("%s wins with %s.", player2.getKey(), highestValueCardPlayer2.get().toString());
        }
    }
}
