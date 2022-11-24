package taskdb.taskdb.domain.notification.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import taskdb.taskdb.domain.user.domain.User;

import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Optional<Notification> findByUser(User user);
}
