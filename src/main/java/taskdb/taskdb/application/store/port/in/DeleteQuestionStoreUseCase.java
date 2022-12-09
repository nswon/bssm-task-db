package taskdb.taskdb.application.store.port.in;

public interface DeleteQuestionStoreUseCase {
    void delete(Long id);
    void delete();
}
