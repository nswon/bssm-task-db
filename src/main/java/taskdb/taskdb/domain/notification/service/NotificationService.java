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
    public void sendByCreateComment(String nickname) {
        List<String> tokens = notificationFacade.getTokens();
        tokens.forEach(token -> {
            sendPushNotification(token, nickname);
        });
    }

    private void sendPushNotification(String token, String nickname) {
        Message message = Message.builder()
                .setWebpushConfig(WebpushConfig.builder()
                        .setNotification(WebpushNotification.builder()
                                .setTitle("TaskDB")
                                .setBody(nickname + "님이 댓글을 등록하였습니다.")
                                .build())
                        .build())
                .setToken(token)
                .build();

        try {
            FirebaseMessaging.getInstance().sendAsync(message).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
