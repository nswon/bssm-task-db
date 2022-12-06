package taskdb.taskdb.domain.notification.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.notification.domain.Notification;
import taskdb.taskdb.domain.notification.port.NotificationReader;
import taskdb.taskdb.domain.notification.port.NotificationStore;
import taskdb.taskdb.domain.notification.repository.NotificationRepository;
import taskdb.taskdb.domain.notification.exception.InvalidNotificationException;
import taskdb.taskdb.domain.notification.facade.NotificationFacade;
import taskdb.taskdb.domain.notification.dto.NotificationPermitRequestDto;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;
import taskdb.taskdb.domain.user.port.UserReader;
import taskdb.taskdb.mapper.NotificationMapper;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {
    private final UserReader userReader;
    private final NotificationMapper notificationMapper;
    private final NotificationStore notificationStore;
    private final NotificationFacade notificationFacade;
    private final NotificationReader notificationReader;

    public void permit(NotificationPermitRequestDto requestDto) {
        User user = userReader.getCurrentUser();
        Notification notification = notificationMapper.of(requestDto, user);
        notificationStore.store(notification);
    }

    @Transactional(readOnly = true)
    public void sendByCreateAnswer(String nickname, User questionWriter) {
        List<String> tokens = notificationFacade.getTokenByCommentUsers(questionWriter);
        tokens.forEach(token -> sendMessage(token, nickname));
    }

    private void sendMessage(String token, String nickname) {
        try {
            sendPushNotificationByAnswer(token, nickname);
        } catch (ExecutionException | InterruptedException e) {
            throw new InvalidNotificationException();
        }
    }

    private void sendPushNotificationByAnswer(String token, String nickname) throws ExecutionException, InterruptedException {
        Message message = notificationFacade.createPushMessage(token, nickname);
        FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    public void deleteTokenByUser() {
        User user = userReader.getCurrentUser();
        Notification notification = notificationReader.getNotificationByUser(user);
        notificationStore.delete(notification);
    }
}
