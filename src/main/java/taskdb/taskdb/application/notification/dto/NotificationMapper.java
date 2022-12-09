package taskdb.taskdb.application.notification.dto;

import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.notification.entity.Notification;
import taskdb.taskdb.application.notification.dto.NotificationPermitRequestDto;
import taskdb.taskdb.domain.user.entity.User;

@Component
public class NotificationMapper {
    public Notification of(NotificationPermitRequestDto requestDto, User user) {
        return Notification.builder()
                .token(requestDto.getToken())
                .user(user)
                .build();
    }
}
