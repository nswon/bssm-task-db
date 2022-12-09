package taskdb.taskdb.application.notification.dto;

import lombok.Getter;
import lombok.ToString;

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
