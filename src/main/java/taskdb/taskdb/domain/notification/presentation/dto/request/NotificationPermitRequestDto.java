package taskdb.taskdb.domain.notification.presentation.dto.request;

import lombok.Getter;
import taskdb.taskdb.domain.notification.domain.Notification;

@Getter
public class NotificationPermitRequestDto {
    private String token;

    public Notification toEntity() {
        return Notification.builder()
                .token(token)
                .build();
    }
}
