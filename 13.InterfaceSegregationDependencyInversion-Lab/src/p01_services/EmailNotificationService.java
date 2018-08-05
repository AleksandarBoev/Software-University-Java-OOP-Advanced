package p01_services;

public class EmailNotificationService implements NotificationService {
    private String emailAddress;

    public EmailNotificationService(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean isActive() {
        //validation that the email exists
        return true;
    }

    @Override
    public void sendNotification() {
        //sends info on the email
    }
}
