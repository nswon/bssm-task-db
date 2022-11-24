package taskdb.taskdb.domain.notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.notification.domain.Notification;
import taskdb.taskdb.domain.notification.domain.NotificationRepository;
import taskdb.taskdb.domain.notification.presentation.dto.request.NotificationPermitRequestDto;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserFacade userFacade;

    public void permit(NotificationPermitRequestDto requestDto) {
        User user = userFacade.getCurrentUser();
        Notification notification = requestDto.toEntity();
        notification.confirmUser(user);
        notificationRepository.save(notification);
    }
}
