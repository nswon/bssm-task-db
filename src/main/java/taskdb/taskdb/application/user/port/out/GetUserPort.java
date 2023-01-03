package taskdb.taskdb.application.user.port.out;

import taskdb.taskdb.domain.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface GetUserPort {
    User getUser(UUID id);
    User getCurrentUser();
    User getUserByEmail(String email);
    List<User> getUser();
}