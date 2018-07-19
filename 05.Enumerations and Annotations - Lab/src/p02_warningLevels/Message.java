package p02_warningLevels;

public class Message {
    private Importance importance;
    private String message;

    public Message(Importance importance, String message) {
        this.importance = importance;
        this.message = message;
    }

    public Importance getImportance() {
        return this.importance;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.importance.name(), this.message);
    }
}
