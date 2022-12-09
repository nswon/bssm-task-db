package taskdb.taskdb.application.notification.port.out;

import taskdb.taskdb.domain.user.entity.User;

public interface DeleteUserDeviceTokenPort {
    void delete(User user);
}
