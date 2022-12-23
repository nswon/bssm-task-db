package taskdb.taskdb.adapter.auth.out;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import taskdb.taskdb.domain.auth.entity.RefreshToken;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}