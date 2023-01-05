package taskdb.taskdb.adapter.question.out.persistence;

import org.springframework.data.repository.CrudRepository;
import taskdb.taskdb.domain.question.entity.VisitQuestion;

import java.util.UUID;

public interface VisitQuestionRepository extends CrudRepository<VisitQuestion, String> {
    boolean existsByQuestionId(UUID id);
}
