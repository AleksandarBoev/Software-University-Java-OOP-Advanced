package p06_customEnumAnnotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cardCategory = reader.readLine();
        reader.close();

        String type = "";
        String category = "";
        String description = "";
        if ("Rank".equals(cardCategory)) {
            type = CardRanks.class.getAnnotation(CustomAnnotation.class).type();
            category = CardRanks.class.getAnnotation(CustomAnnotation.class).category();
            description = CardRanks.class.getAnnotation(CustomAnnotation.class).description();
        } else {
            type = CardSuits.class.getAnnotation(CustomAnnotation.class).type();
            category = CardSuits.class.getAnnotation(CustomAnnotation.class).category();
            description = CardSuits.class.getAnnotation(CustomAnnotation.class).description();
        }

        System.out.printf("Type = %s, Description = %s", type, description);

    }
}
