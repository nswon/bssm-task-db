package taskdb.taskdb.domain.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailValue(String email);
    Optional<User> findByEmailValueAndPasswordValue(String email, String password);
}
