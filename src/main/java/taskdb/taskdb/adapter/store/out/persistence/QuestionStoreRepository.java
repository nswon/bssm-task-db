package taskdb.taskdb.adapter.store.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import taskdb.taskdb.domain.store.entity.QuestionStore;
import taskdb.taskdb.domain.user.entity.User;

import java.util.List;

public interface QuestionStoreRepository extends JpaRepository<QuestionStore, Long> {
    boolean existsByUserAndQuestionId(User user, Long questionId);
    List<QuestionStore> findByUser(User user);
}
