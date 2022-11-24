package taskdb.taskdb.domain.notification.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import taskdb.taskdb.domain.notification.domain.Notification;

@Getter
@AllArgsConstructor
public class NotificationPermitRequestDto {
    private final String token;

    public Notification toEntity() {
        return Notification.builder()
                .token(token)
                .build();
    }
}
