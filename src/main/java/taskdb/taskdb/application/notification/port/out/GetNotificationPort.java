package taskdb.taskdb.application.notification.port.out;

import taskdb.taskdb.domain.notification.entity.Notification;
import taskdb.taskdb.domain.user.entity.User;

public interface GetNotificationPort {
    Notification getNotification(User user);
}
