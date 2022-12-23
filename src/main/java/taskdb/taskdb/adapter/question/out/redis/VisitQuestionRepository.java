package taskdb.taskdb.adapter.question.out.redis;

import org.springframework.data.repository.CrudRepository;
import taskdb.taskdb.domain.question.entity.VisitQuestion;

import java.util.List;
import java.util.Optional;

public interface VisitQuestionRepository extends CrudRepository<VisitQuestion, String> {
    List<VisitQuestion> findAll();
    boolean existsByQuestionId(Long id);
}
