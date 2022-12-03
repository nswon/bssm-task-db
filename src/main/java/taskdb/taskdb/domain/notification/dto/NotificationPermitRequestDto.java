package taskdb.taskdb.domain.notification.dto;

import lombok.Getter;
import lombok.ToString;
import taskdb.taskdb.domain.notification.domain.Notification;

@Getter
@ToString
public class NotificationPermitRequestDto {
    private String token;

    public NotificationPermitRequestDto() {
    }

    public NotificationPermitRequestDto(String token) {
        this.token = token;
    }
}
