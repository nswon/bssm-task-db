package taskdb.taskdb.adapter.notification.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.application.notification.dto.NotificationPermitRequestDto;
import taskdb.taskdb.application.notification.port.in.NotificationDeleteUseCase;
import taskdb.taskdb.application.notification.port.in.NotificationSaveUseCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationSaveUseCase notificationSaveUseCase;
    private final NotificationDeleteUseCase notificationDeleteUseCase;

    @PostMapping("/permit")
    public void permit(@RequestBody NotificationPermitRequestDto requestDto) {
        notificationSaveUseCase.permit(requestDto);
    }

    @DeleteMapping("/delete")
    public void delete() {
        notificationDeleteUseCase.delete();
    }
}
