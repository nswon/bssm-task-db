package taskdb.taskdb.application.user.port.out;

import taskdb.taskdb.domain.user.entity.User;

import java.util.List;

public interface GetUserPort {
    User getUser(Long id);
    User getCurrentUser();
    User getUserByEmail(String email);
    List<User> getUser();
}