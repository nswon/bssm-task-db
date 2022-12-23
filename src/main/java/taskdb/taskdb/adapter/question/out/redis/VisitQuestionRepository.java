package taskdb.taskdb.adapter.question.out.redis;

import java.util.List;
import java.util.Optional;

public interface VisitQuestionRepository {
    void save(Long id);
    Optional<List<String>> existsQuestionId(Long id);
}
