package taskdb.taskdb.application.question.port.in;

import java.util.UUID;

public interface QuestionDeleteUseCase {
    void delete(UUID id);
}
