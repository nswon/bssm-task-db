package taskdb.taskdb.infrastructure.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.store.domain.QuestionStore;
import taskdb.taskdb.domain.store.port.QuestionStoreStore;
import taskdb.taskdb.domain.store.repository.QuestionStoreRepository;

@Component
@RequiredArgsConstructor
public class QuestionStoreStoreImpl implements QuestionStoreStore {
    private final QuestionStoreRepository questionStoreRepository;

    @Override
    public QuestionStore store(QuestionStore questionStore) {
        validate(questionStore);
        questionStoreRepository.save(questionStore);
        return questionStore;
    }

    @Override
    public QuestionStore delete(QuestionStore questionStore) {
        questionStoreRepository.delete(questionStore);
        return questionStore;
    }

    @Override
    public void deleteAll() {
        questionStoreRepository.deleteAllInBatch();
    }

    private void validate(QuestionStore questionStore) {

    }
}
