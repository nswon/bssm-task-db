package taskdb.taskdb.adapter.question.out.persistence;

import org.springframework.data.repository.CrudRepository;
import taskdb.taskdb.domain.question.entity.VisitQuestion;

public interface VisitQuestionRepository extends CrudRepository<VisitQuestion, String> {
    boolean existsByQuestionId(Long id);
}
