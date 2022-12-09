package taskdb.taskdb.adapter.notification.out;

import org.springframework.data.jpa.repository.JpaRepository;
import taskdb.taskdb.domain.notification.entity.Notification;
import taskdb.taskdb.domain.user.entity.User;

import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Optional<Notification> findByUser(User user);
}
