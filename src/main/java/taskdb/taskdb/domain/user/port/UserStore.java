package taskdb.taskdb.domain.user.port;

import taskdb.taskdb.domain.user.domain.User;

public interface UserStore {
    User store(User user);
}
