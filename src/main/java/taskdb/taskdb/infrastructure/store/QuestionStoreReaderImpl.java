package taskdb.taskdb.infrastructure.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.store.domain.QuestionStore;
import taskdb.taskdb.domain.store.exception.StoreQuestionNotFoundException;
import taskdb.taskdb.domain.store.port.QuestionStoreReader;
import taskdb.taskdb.domain.store.repository.QuestionStoreRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionStoreReaderImpl implements QuestionStoreReader {
    private final QuestionStoreRepository questionStoreRepository;

    @Override
    public List<QuestionStore> getQuestions() {
        return questionStoreRepository.findAll();
    }

    @Override
    public QuestionStore getQuestionStore(Long id) {
        return questionStoreRepository.findById(id)
                .orElseThrow(StoreQuestionNotFoundException::new);
    }
}
