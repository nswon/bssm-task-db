package taskdb.taskdb.adapter.store.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import taskdb.taskdb.application.store.port.out.DeleteQuestionStorePort;
import taskdb.taskdb.application.store.port.out.GetQuestionStorePort;
import taskdb.taskdb.application.store.port.out.SaveQuestionStorePort;
import taskdb.taskdb.domain.store.entity.QuestionStore;
import taskdb.taskdb.domain.store.exception.DuplicateQuestionStoreException;
import taskdb.taskdb.domain.store.exception.StoreQuestionNotFoundException;
import taskdb.taskdb.domain.user.entity.User;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class QuestionStoreAdapter implements SaveQuestionStorePort, GetQuestionStorePort, DeleteQuestionStorePort {
    private final QuestionStoreRepository questionStoreRepository;
    private final QuestionStoreCustomRepository questionStoreCustomRepository;

    @Override
    public QuestionStore save(User user, QuestionStore questionStore) {
        validate(user, questionStore.getQuestionId());
        return questionStoreRepository.save(questionStore);
    }

    private void validate(User user, Long id) {
        if(questionStoreRepository.existsByUserAndQuestionId(user, id)) {
            throw new DuplicateQuestionStoreException();
        }
    }

    @Override
    public List<QuestionStore> getQuestions(User user) {
        return questionStoreRepository.findByUser(user);
    }

    @Override
    public QuestionStore getQuestionStore(User user, UUID id) {
        return questionStoreCustomRepository.getQuestionStoreByUser(user, id)
                .orElseThrow(StoreQuestionNotFoundException::new);
    }

    @Override
    public void delete(QuestionStore questionStore) {
        questionStoreRepository.delete(questionStore);
    }

    @Override
    public void delete() {
        questionStoreRepository.deleteAllInBatch();
    }
}
