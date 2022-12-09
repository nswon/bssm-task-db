package taskdb.taskdb.application.notification.port.in;

import taskdb.taskdb.application.notification.dto.NotificationPermitRequestDto;

public interface NotificationSaveUseCase {
    void permit(NotificationPermitRequestDto requestDto);
}
