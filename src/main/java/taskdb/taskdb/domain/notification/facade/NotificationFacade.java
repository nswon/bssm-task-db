package taskdb.taskdb.domain.notification.facade;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.notification.domain.Notification;
import taskdb.taskdb.domain.notification.port.NotificationReader;
import taskdb.taskdb.domain.notification.repository.NotificationRepository;
import taskdb.taskdb.domain.notification.exception.UserDeviceTokenNotFoundException;
import taskdb.taskdb.domain.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NotificationFacade {
    private static final String PUSH_NOTIFICATION_TITLE = "TaskDB";
    private static final String PUSH_NOTIFICATION_BODY = "님이 답변을 등록하였습니다.";
    private final NotificationReader notificationReader;

    public List<String> getTokenByCommentUsers(User questionWriter) {
        List<Comment> comments = questionWriter.getComments();
        List<String> tokens = comments.stream()
                .map(Comment::getUser)
                .map(notificationReader::getNotificationByUser)
                .map(Notification::getToken)
                .collect(Collectors.toList());

        String token = getQuestionWriterToken(questionWriter);
        tokens.add(token);
        return tokens;
    }

    private String getQuestionWriterToken(User questionWriter) {
        Notification notification = notificationReader.getNotificationByUser(questionWriter);
        return notification.getToken();
    }

    public Message createPushMessage(String token, String nickname) {
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
