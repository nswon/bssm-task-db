package taskdb.taskdb.adapter.user.out.persistence;

import taskdb.taskdb.domain.user.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserCustomRepository {
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
}
