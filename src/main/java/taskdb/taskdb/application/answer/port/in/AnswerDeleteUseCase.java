package taskdb.taskdb.application.answer.port.in;

import java.util.UUID;

public interface AnswerDeleteUseCase {
    void delete(UUID id);
}
