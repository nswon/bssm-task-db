package taskdb.taskdb.domain.user.port;

import taskdb.taskdb.domain.user.domain.User;

public interface UserReader {
    User getUser(Long id);
    User getCurrentUser();
    User getUserByEmail(String email);
}
