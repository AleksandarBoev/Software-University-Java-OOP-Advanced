package p01_services;

public class SmsNotificationService implements NotificationService {
    private String phoneNumber;

    public SmsNotificationService(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean isActive() {
        //validation that the number exists
        return true;
    }

    @Override
    public void sendNotification() {
        //sends sms
    }
}
