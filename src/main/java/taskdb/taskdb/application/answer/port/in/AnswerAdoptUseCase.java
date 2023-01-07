package taskdb.taskdb.application.answer.port.in;

import java.util.UUID;

public interface AnswerAdoptUseCase {
    void adopt(UUID id);
}
