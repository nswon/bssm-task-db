package taskdb.taskdb.mapper;

import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.notification.domain.Notification;
import taskdb.taskdb.domain.notification.dto.NotificationPermitRequestDto;
import taskdb.taskdb.domain.user.domain.User;

@Component
public class NotificationMapper {
    public Notification of(NotificationPermitRequestDto requestDto, User user) {
        return Notification.builder()
                .token(requestDto.getToken())
                .user(user)
                .build();
    }
}
