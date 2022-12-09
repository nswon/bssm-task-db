package taskdb.taskdb.adapter.notification.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.application.notification.port.out.DeleteUserDeviceTokenPort;
import taskdb.taskdb.application.notification.port.out.GetNotificationPort;
import taskdb.taskdb.application.notification.port.out.SaveUserDeviceTokenPort;
import taskdb.taskdb.domain.notification.entity.Notification;
import taskdb.taskdb.domain.notification.exception.UserDeviceTokenNotFoundException;
import taskdb.taskdb.domain.user.entity.User;

@Component
@RequiredArgsConstructor
public class NotificationAdapter implements SaveUserDeviceTokenPort, DeleteUserDeviceTokenPort, GetNotificationPort {
    private final NotificationRepository notificationRepository;

    @Override
    public Notification save(Notification notification) {
        notificationRepository.save(notification);
        return notification;
    }

    @Override
    public void delete(User user) {
        Notification notification = notificationRepository.findByUser(user)
                        .orElseThrow(UserDeviceTokenNotFoundException::new);
        notificationRepository.delete(notification);
    }

    @Override
    public Notification getNotification(User user) {
        return notificationRepository.findByUser(user)
                .orElseThrow(UserDeviceTokenNotFoundException::new);
    }
}
