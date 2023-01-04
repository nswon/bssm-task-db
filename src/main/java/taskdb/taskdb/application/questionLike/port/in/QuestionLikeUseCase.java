package taskdb.taskdb.application.questionLike.port.in;

import java.util.UUID;

public interface QuestionLikeUseCase {
    void like(UUID id);
}
