package taskdb.taskdb.adapter.auth.out;

import org.springframework.data.repository.CrudRepository;
import taskdb.taskdb.domain.auth.entity.RefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}