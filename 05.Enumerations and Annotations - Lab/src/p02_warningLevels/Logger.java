package p02_warningLevels;

import java.util.ArrayList;
import java.util.Collection;

public class Logger {
    Collection<Message> messages;
    Importance minimumImportance;

    public Logger(Importance minimumImportance) {
        this.minimumImportance = minimumImportance;
        this.messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        if (message.getImportance().compareTo(this.minimumImportance) >= 0) {
            this.messages.add(message);
        }
    }

    public Iterable<Message> getMessages() {
        return this.messages;
    }
}
