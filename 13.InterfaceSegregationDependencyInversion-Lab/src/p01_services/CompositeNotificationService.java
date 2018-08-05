package p01_services;

import java.util.ArrayList;
import java.util.List;

public class CompositeNotificationService implements NotificationService {
    private List<NotificationService> notificationServices;

    public CompositeNotificationService() {
        this.notificationServices = new ArrayList<>();
    }

    public void addNotificationService(NotificationService notificationService) {
        this.notificationServices.add(notificationService);
    }

    @Override
    public boolean isActive() {
        if (this.notificationServices.isEmpty())
            return false;

        for (NotificationService notificationService : this.notificationServices) { //all services need to be active!
            if (!notificationService.isActive())
                return false;
        }
        return true;
    }

    @Override
    public void sendNotification() {
        if (this.isActive()) {
            for (NotificationService notificationService : this.notificationServices) {
                notificationService.sendNotification();
            }
        }
    }
}
