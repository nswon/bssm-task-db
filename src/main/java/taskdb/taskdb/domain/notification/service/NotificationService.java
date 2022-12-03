package taskdb.taskdb.domain.notification.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.notification.domain.Notification;
import taskdb.taskdb.domain.notification.domain.NotificationRepository;
import taskdb.taskdb.domain.notification.exception.InvalidNotificationException;
import taskdb.taskdb.domain.notification.facade.NotificationFacade;
import taskdb.taskdb.domain.notification.presentation.dto.request.NotificationPermitRequestDto;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserFacade userFacade;
    private final NotificationFacade notificationFacade;

    public void permit(NotificationPermitRequestDto requestDto) {
        User user = userFacade.getCurrentUser();
        Notification notification = requestDto.toEntity();
        notification.confirmUser(user);
        notificationRepository.save(notification);
    }

    @Transactional(readOnly = true)
    public void sendByCreateAnswer(String nickname, User questionWriter) {
        List<String> tokens = notificationFacade.getTokenByCommentUsers(questionWriter);
        tokens.forEach(token -> sendMessage(token, nickname));
    }

    public void sendMessage(String token, String nickname) {
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
        User user = userFacade.getCurrentUser();
        Notification notification = notificationFacade.getNotificationByUser(user);
        notificationRepository.delete(notification);
    }
}
