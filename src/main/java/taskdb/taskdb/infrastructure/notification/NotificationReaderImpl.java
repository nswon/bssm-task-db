package taskdb.taskdb.infrastructure.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.notification.domain.Notification;
import taskdb.taskdb.domain.notification.exception.UserDeviceTokenNotFoundException;
import taskdb.taskdb.domain.notification.port.NotificationReader;
import taskdb.taskdb.domain.notification.repository.NotificationRepository;
import taskdb.taskdb.domain.user.domain.User;

@Component
@RequiredArgsConstructor
public class NotificationReaderImpl implements NotificationReader {
    private final NotificationRepository notificationRepository;

    @Override
    public Notification getNotificationByUser(User user) {
        return notificationRepository.findByUser(user)
                .orElseThrow(UserDeviceTokenNotFoundException::new);
    }
}
