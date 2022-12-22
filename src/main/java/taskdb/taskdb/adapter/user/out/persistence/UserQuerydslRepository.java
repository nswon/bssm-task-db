package taskdb.taskdb.adapter.user.out.persistence;

import taskdb.taskdb.domain.user.entity.User;

import java.util.Optional;

public interface UserQuerydslRepository {
    Optional<User> findById(Long id);
}
