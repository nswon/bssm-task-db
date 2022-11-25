package taskdb.taskdb.domain.store.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.store.domain.QuestionStore;
import taskdb.taskdb.domain.store.domain.QuestionStoreRepository;
import taskdb.taskdb.domain.store.exception.QuestionStoreException;
import taskdb.taskdb.domain.store.exception.QuestionStoreExceptionType;

@Component
@RequiredArgsConstructor
public class QuestionStoreFacade {
    private final QuestionStoreRepository questionStoreRepository;

    public QuestionStore getQuestionStoreById(Long id) {
        return questionStoreRepository.findById(id)
                .orElseThrow(() -> new QuestionStoreException(QuestionStoreExceptionType.NOT_FOUND_STORE_QUESTION));
    }
}
