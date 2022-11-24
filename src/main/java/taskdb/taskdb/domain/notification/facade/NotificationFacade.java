package taskdb.taskdb.domain.notification.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.notification.domain.Notification;
import taskdb.taskdb.domain.notification.domain.NotificationRepository;
import taskdb.taskdb.domain.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NotificationFacade {
    private final NotificationRepository notificationRepository;

    public List<String> getTokenByUserAndCommentUsers(User questionWriter) {
        List<Comment> comments = questionWriter.getComments();
        List<String> tokens = comments.stream()
                .map(Comment::getUser)
                .map(user -> notificationRepository.findByUser(user)
                        .orElseThrow())
                .map(Notification::getToken)
                .collect(Collectors.toList());

        Notification notification = notificationRepository.findByUser(questionWriter).orElseThrow();
        String token = notification.getToken();
        tokens.add(token);
        return tokens;
    }
}
