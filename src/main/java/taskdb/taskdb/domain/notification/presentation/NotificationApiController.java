package taskdb.taskdb.domain.notification.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.domain.notification.presentation.dto.request.NotificationPermitRequestDto;
import taskdb.taskdb.domain.notification.service.NotificationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationApiController {
    private final NotificationService notificationService;

    @PostMapping("/permit")
    public void permit(@RequestBody NotificationPermitRequestDto requestDto) {
        notificationService.permit(requestDto);
    }

    @DeleteMapping("/delete")
    public void delete() {
        notificationService.deleteTokenByUser();
    }
}
