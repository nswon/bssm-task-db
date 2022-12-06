package taskdb.taskdb.domain.notification.port;

import taskdb.taskdb.domain.notification.domain.Notification;
import taskdb.taskdb.domain.user.domain.User;

public interface NotificationReader {
    Notification getNotificationByUser(User user);
}
