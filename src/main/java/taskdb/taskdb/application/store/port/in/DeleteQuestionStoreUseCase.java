package taskdb.taskdb.application.store.port.in;

import java.util.UUID;

public interface DeleteQuestionStoreUseCase {
    void delete(UUID id);
    void delete();
}
