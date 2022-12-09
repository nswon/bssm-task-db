package taskdb.taskdb.application.notification.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.notification.port.in.NotificationDeleteUseCase;
import taskdb.taskdb.application.notification.port.in.NotificationSaveUseCase;
import taskdb.taskdb.application.notification.port.out.DeleteUserDeviceTokenPort;
import taskdb.taskdb.application.notification.port.out.GetNotificationPort;
import taskdb.taskdb.application.notification.port.out.SaveUserDeviceTokenPort;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.notification.entity.Notification;
import taskdb.taskdb.application.notification.dto.NotificationPermitRequestDto;
import taskdb.taskdb.domain.notification.exception.InvalidNotificationException;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.application.user.port.out.GetUserPort;
import taskdb.taskdb.application.notification.dto.NotificationMapper;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService implements NotificationSaveUseCase, NotificationDeleteUseCase {
    private static final String PUSH_NOTIFICATION_TITLE = "TaskDB";
    private static final String PUSH_NOTIFICATION_BODY = "님이 답변을 등록하였습니다.";
    private final GetUserPort getUserPort;
    private final NotificationMapper notificationMapper;
    private final SaveUserDeviceTokenPort saveUserDeviceTokenPort;
    private final DeleteUserDeviceTokenPort deleteUserDeviceTokenPort;
    private final GetNotificationPort getNotificationPort;

    @Override
    public void permit(NotificationPermitRequestDto requestDto) {
        User user = getUserPort.getCurrentUser();
        Notification notification = notificationMapper.of(requestDto, user);
        saveUserDeviceTokenPort.save(notification);
    }

    @Transactional(readOnly = true)
    public void sendMessage(String nickname, User questionWriter) {
        List<String> tokens = getTokenByCommentUsers(questionWriter);
        tokens.forEach(token -> send(token, nickname));
    }

    @Override
    public void delete() {
        User user = getUserPort.getCurrentUser();
        deleteUserDeviceTokenPort.delete(user);
    }

    private void send(String token, String nickname) {
        try {
            sendPushNotificationByAnswer(token, nickname);
        } catch (ExecutionException | InterruptedException e) {
            throw new InvalidNotificationException();
        }
    }

    private void sendPushNotificationByAnswer(String token, String nickname) throws ExecutionException, InterruptedException {
        Message message = createPushMessage(token, nickname);
        FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    private List<String> getTokenByCommentUsers(User questionWriter) {
        List<Comment> comments = questionWriter.getComments();
        List<String> tokens = comments.stream()
                .map(Comment::getUser)
                .map(getNotificationPort::getNotification)
                .map(Notification::getToken)
                .collect(Collectors.toList());

        String token = getQuestionWriterToken(questionWriter);
        tokens.add(token);
        return tokens;
    }

    private String getQuestionWriterToken(User questionWriter) {
        Notification notification = getNotificationPort.getNotification(questionWriter);
        return notification.getToken();
    }

    private Message createPushMessage(String token, String nickname) {
        return Message.builder()
                .setWebpushConfig(WebpushConfig.builder()
                        .setNotification(WebpushNotification.builder()
                                .setTitle(PUSH_NOTIFICATION_TITLE)
                                .setBody(nickname + PUSH_NOTIFICATION_BODY)
                                .build())
                        .build())
                .setToken(token)
                .build();
    }
}
