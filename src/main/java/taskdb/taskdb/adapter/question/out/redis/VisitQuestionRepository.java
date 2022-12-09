package taskdb.taskdb.adapter.question.out.redis;

import java.util.List;

public interface VisitQuestionRepository {
    void save(Long id);
    List<String> getQuestionIds();
}
