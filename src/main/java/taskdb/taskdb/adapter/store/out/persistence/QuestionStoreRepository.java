package taskdb.taskdb.adapter.store.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import taskdb.taskdb.domain.store.entity.QuestionStore;

public interface QuestionStoreRepository extends JpaRepository<QuestionStore, Long> {
}
