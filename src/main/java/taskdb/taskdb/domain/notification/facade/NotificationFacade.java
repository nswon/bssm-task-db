package taskdb.taskdb.domain.notification.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.notification.domain.Notification;
import taskdb.taskdb.domain.notification.domain.NotificationRepository;
import taskdb.taskdb.domain.notification.exception.NotificationException;
import taskdb.taskdb.domain.notification.exception.NotificationExceptionType;
import taskdb.taskdb.domain.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NotificationFacade {
    private final NotificationRepository notificationRepository;

    public List<String> getTokenByCommentUsers(User questionWriter) {
        List<Comment> comments = questionWriter.getComments();
        List<String> tokens = comments.stream()
                .map(Comment::getUser)
                .map(this::getNotificationByUser)
                .map(Notification::getToken)
                .collect(Collectors.toList());

        String token = getQuestionWriterToken(questionWriter);
        tokens.add(token);
        return tokens;
    }

    private String getQuestionWriterToken(User questionWriter) {
        Notification notification = getNotificationByUser(questionWriter);
        return notification.getToken();
    }

    public Notification getNotificationByUser(User user) {
        return notificationRepository.findByUser(user)
                .orElseThrow(() -> new NotificationException(NotificationExceptionType.NOT_FOUND_TOKEN));
    }
}
