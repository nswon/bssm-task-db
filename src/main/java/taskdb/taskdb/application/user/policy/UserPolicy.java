package taskdb.taskdb.application.user.policy;

import taskdb.taskdb.domain.user.entity.User;

public interface UserPolicy {
    void check(User currentUser, User writer);
}
