package taskdb.taskdb.adapter.store.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.application.store.port.out.DeleteQuestionStorePort;
import taskdb.taskdb.application.store.port.out.GetQuestionStorePort;
import taskdb.taskdb.application.store.port.out.SaveQuestionStorePort;
import taskdb.taskdb.domain.store.entity.QuestionStore;
import taskdb.taskdb.domain.store.exception.DuplicateQuestionStoreException;
import taskdb.taskdb.domain.store.exception.StoreQuestionNotFoundException;
import taskdb.taskdb.domain.user.entity.User;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionStoreAdapter implements SaveQuestionStorePort, GetQuestionStorePort, DeleteQuestionStorePort {
    private final QuestionStoreRepository questionStoreRepository;

    @Override
    public QuestionStore save(User user, QuestionStore questionStore) {
        validate(user, questionStore);
        return questionStoreRepository.save(questionStore);
    }

    private void validate(User user, QuestionStore target) {
        boolean isDuplicate = questionStoreRepository.existsByUserAndQuestionId(user, target.getQuestionId());
        if(isDuplicate) {
            throw new DuplicateQuestionStoreException();
        }
    }

    @Override
    public List<QuestionStore> getQuestions(User user) {
        return questionStoreRepository.findByUser(user);
    }

    @Override
    public QuestionStore getQuestionStore(User user, Long id) {
        List<QuestionStore> questionStores = questionStoreRepository.findByUser(user);
        return questionStores.stream()
                .filter(questionStore -> questionStore.getId().equals(id))
                .findAny()
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
