package taskdb.taskdb.domain.store.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.store.domain.QuestionStore;
import taskdb.taskdb.domain.store.repository.QuestionStoreRepository;
import taskdb.taskdb.domain.store.exception.StoreQuestionNotFoundException;

@Component
@RequiredArgsConstructor
public class QuestionStoreFacade {
    private final QuestionStoreRepository questionStoreRepository;

    public QuestionStore getQuestionStoreById(Long id) {
        return questionStoreRepository.findById(id)
                .orElseThrow(StoreQuestionNotFoundException::new);
    }
}
