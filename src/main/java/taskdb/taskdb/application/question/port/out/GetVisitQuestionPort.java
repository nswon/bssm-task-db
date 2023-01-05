package taskdb.taskdb.application.question.port.out;

import java.util.UUID;

public interface GetVisitQuestionPort {
    boolean hasQuestionId(UUID id);
}
