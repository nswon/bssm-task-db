package taskdb.taskdb.infrastructure.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.notification.domain.Notification;
import taskdb.taskdb.domain.notification.port.NotificationStore;
import taskdb.taskdb.domain.notification.repository.NotificationRepository;

@Component
@RequiredArgsConstructor
public class NotificationStoreImpl implements NotificationStore {
    private final NotificationRepository notificationRepository;

    @Override
    public Notification store(Notification notification) {
        notificationRepository.save(notification);
        return notification;
    }

    @Override
    public void delete(Notification notification) {
        notificationRepository.delete(notification);
    }
}
