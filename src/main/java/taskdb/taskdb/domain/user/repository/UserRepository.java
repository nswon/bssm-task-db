package taskdb.taskdb.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskdb.taskdb.domain.user.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailValue(String email);
    Optional<User> findByEmailValueAndPasswordValue(String email, String password);
}
