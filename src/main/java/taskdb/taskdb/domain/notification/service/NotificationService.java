package taskdb.taskdb.domain.notification.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.notification.domain.Notification;
import taskdb.taskdb.domain.notification.domain.NotificationRepository;
import taskdb.taskdb.domain.notification.exception.NotificationException;
import taskdb.taskdb.domain.notification.exception.NotificationExceptionType;
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
    private static final String PUSH_NOTIFICATION_TITLE = "TaskDB";
    private static final String PUSH_NOTIFICATION_BODY = "님이 답변을 등록하였습니다.";
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
        tokens.forEach(token -> sendPushNotificationByAnswer(token, nickname));
    }

    private void sendPushNotificationByAnswer(String token, String nickname) {
        Message message = Message.builder()
                .setWebpushConfig(WebpushConfig.builder()
                        .setNotification(WebpushNotification.builder()
                                .setTitle(PUSH_NOTIFICATION_TITLE)
                                .setBody(nickname + PUSH_NOTIFICATION_BODY)
                                .build())
                        .build())
                .setToken(token)
                .build();

        try {
            FirebaseMessaging.getInstance().sendAsync(message).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new NotificationException(NotificationExceptionType.FAIL_SEND);
        }
    }

    public void deleteTokenByUser() {
        User user = userFacade.getCurrentUser();
        Notification notification = notificationFacade.getNotificationByUser(user);
        notificationRepository.delete(notification);
    }
}
