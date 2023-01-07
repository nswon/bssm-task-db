package taskdb.taskdb.application.answerLike.port.in;

import java.util.UUID;

public interface AnswerLikeUseCase {
    void like(UUID id);
}