package p02_warningLevels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        //HIGH
        //NORMAL: All systems running
        //HIGH: Leakage in core room
        //LOW: Food delivery
        //END
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Importance minimumImportance = Importance.valueOf(Importance.class, reader.readLine());
        Logger logger = new Logger(minimumImportance);

        String input;
        while(!"END".equals(input = reader.readLine())) {
            String[] tokens = input.split(": ");

            Message currentMessage = new Message(Importance.valueOf(Importance.class, tokens[0]), tokens[1]);
            logger.addMessage(currentMessage);
        }
        reader.close();

        Iterable<Message> messageIterable = logger.getMessages();

        for (Message message : messageIterable) {
            System.out.println(message);
        }
    }
}
