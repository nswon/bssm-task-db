package taskdb.taskdb.application.store.port.in;

import java.util.UUID;

public interface QuestionStoreSaveUseCase {
    void save(UUID id);
}
