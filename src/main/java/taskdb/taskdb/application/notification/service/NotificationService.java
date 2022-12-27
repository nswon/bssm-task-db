package taskdb.taskdb.application.notification.service;

import lombok.RequiredArgsConstructor;
import org.notification.PushMessage;
import org.notification.SendPushNotification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.notification.port.in.NotificationDeleteUseCase;
import taskdb.taskdb.application.notification.port.in.NotificationSaveUseCase;
import taskdb.taskdb.application.notification.port.out.DeleteUserDeviceTokenPort;
import taskdb.taskdb.application.notification.port.out.GetNotificationPort;
import taskdb.taskdb.application.notification.port.out.SaveUserDeviceTokenPort;
import taskdb.taskdb.domain.comment.entity.Comment;
import taskdb.taskdb.domain.notification.entity.Notification;
import taskdb.taskdb.application.notification.dto.NotificationPermitRequestDto;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.application.user.port.out.GetUserPort;
import taskdb.taskdb.application.notification.dto.NotificationMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Async
public class NotificationService implements NotificationSaveUseCase, NotificationDeleteUseCase {
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
    public void sendMessage(String nickname, Question question) {
        List<String> tokens = getTokenByCommentUsers(question);
        tokens.forEach(token -> SendPushNotification.send(of(token, nickname)));
    }

    private PushMessage of(String token, String nickname) {
        return PushMessage.builder()
                .token(token)
                .title("Taskdb")
                .body(nickname + "님이 답변을 등록하였습니다.")
                .build();
    }

    private List<String> getTokenByCommentUsers(Question question) {
        List<Comment> comments = question.getComments();
        List<String> tokens = comments.stream()
                .map(Comment::getUser)
                .distinct()
                .filter(User::hasNotificationToken)
                .map(getNotificationPort::getNotification)
                .map(Notification::getToken)
                .collect(Collectors.toList());

        String token = getQuestionWriterToken(question.getUser());
        tokens.add(token);
        return tokens;
    }

    private String getQuestionWriterToken(User questionWriter) {
        Notification notification = getNotificationPort.getNotification(questionWriter);
        return notification.getToken();
    }

    @Override
    public void delete() {
        User user = getUserPort.getCurrentUser();
        deleteUserDeviceTokenPort.delete(user);
    }
}
