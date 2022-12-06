package taskdb.taskdb.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskdb.taskdb.domain.store.domain.QuestionStore;

import java.util.Optional;

public interface QuestionStoreRepository extends JpaRepository<QuestionStore, Long> {
}
