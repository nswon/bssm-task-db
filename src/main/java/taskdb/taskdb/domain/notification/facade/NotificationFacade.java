package taskdb.taskdb.domain.notification.facade;

import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.notification.domain.Notification;
import taskdb.taskdb.domain.notification.domain.NotificationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NotificationFacade {
    private final NotificationRepository notificationRepository;

    public List<String> getTokens() {
        return notificationRepository.findAll().stream()
                .map(Notification::getToken)
                .collect(Collectors.toList());
    }
}
