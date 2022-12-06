package taskdb.taskdb.domain.notification.port;

import taskdb.taskdb.domain.notification.domain.Notification;

public interface NotificationStore {
    Notification store(Notification notification);
    void delete(Notification notification);
}
