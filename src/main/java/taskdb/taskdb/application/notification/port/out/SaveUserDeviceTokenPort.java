package taskdb.taskdb.application.notification.port.out;

import taskdb.taskdb.domain.notification.entity.Notification;

public interface SaveUserDeviceTokenPort {
    Notification save(Notification notification);
}
