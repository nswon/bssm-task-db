package taskdb.taskdb.adapter.user.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import taskdb.taskdb.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailValue(String email);
    boolean existsUserByNicknameValue(String nickname);
}
