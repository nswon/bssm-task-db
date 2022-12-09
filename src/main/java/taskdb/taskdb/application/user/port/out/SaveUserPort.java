package taskdb.taskdb.application.user.port.out;

import taskdb.taskdb.domain.user.entity.User;

public interface SaveUserPort {
    User save(User user);
}
