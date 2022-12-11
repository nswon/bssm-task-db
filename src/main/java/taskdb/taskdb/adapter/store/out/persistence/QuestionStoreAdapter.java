package taskdb.taskdb.adapter.store.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.application.store.port.out.DeleteQuestionStorePort;
import taskdb.taskdb.application.store.port.out.GetQuestionStorePort;
import taskdb.taskdb.application.store.port.out.SaveQuestionStorePort;
import taskdb.taskdb.domain.store.entity.QuestionStore;
import taskdb.taskdb.domain.store.exception.DuplicateQuestionStoreException;
import taskdb.taskdb.domain.store.exception.StoreQuestionNotFoundException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionStoreAdapter implements SaveQuestionStorePort, GetQuestionStorePort, DeleteQuestionStorePort {
    private final QuestionStoreRepository questionStoreRepository;

    @Override
    public QuestionStore save(QuestionStore questionStore) {
//        validate(questionStore);
        questionStoreRepository.save(questionStore);
        return questionStore;
    }

    @Override
    public List<QuestionStore> getQuestions() {
        return questionStoreRepository.findAll();
    }

    @Override
    public QuestionStore getQuestionStore(Long id) {
        return questionStoreRepository.findById(id)
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

    private void validate(QuestionStore questionStore) {
        boolean isDuplicateQuestion = questionStoreRepository.findById(questionStore.getId()).isPresent();
        if(isDuplicateQuestion) {
            throw new DuplicateQuestionStoreException();
        }
    }
}
