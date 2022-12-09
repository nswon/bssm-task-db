package taskdb.taskdb.application.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.question.port.out.GetQuestionPort;
import taskdb.taskdb.application.store.port.in.DeleteQuestionStoreUseCase;
import taskdb.taskdb.application.store.port.in.GetQuestionStoreUseCase;
import taskdb.taskdb.application.store.port.in.QuestionStoreSaveUseCase;
import taskdb.taskdb.application.store.port.out.DeleteQuestionStorePort;
import taskdb.taskdb.application.store.port.out.GetQuestionStorePort;
import taskdb.taskdb.application.store.port.out.SaveQuestionStorePort;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.store.entity.QuestionStore;
import taskdb.taskdb.application.store.dto.QuestionStoresResponseDto;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.application.user.port.out.GetUserPort;
import taskdb.taskdb.domain.user.exception.DifferentUserException;
import taskdb.taskdb.application.store.dto.QuestionStoreMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionStoreService implements
        QuestionStoreSaveUseCase, GetQuestionStoreUseCase, DeleteQuestionStoreUseCase {
    private final GetUserPort getUserPort;
    private final GetQuestionPort getQuestionPort;
    private final SaveQuestionStorePort saveQuestionStorePort;
    private final GetQuestionStorePort getQuestionsStorePort;
    private final DeleteQuestionStorePort deleteQuestionStorePort;
    private final QuestionStoreMapper questionStoreMapper;

    @Override
    public void save(Long id) {
        User user = getUserPort.getCurrentUser();
        Question question = getQuestionPort.getQuestion(id);
        QuestionStore questionStore = questionStoreMapper.of(question, user);
        saveQuestionStorePort.save(questionStore);
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionStoresResponseDto getQuestions() {
        List<QuestionStore> questionStores = getQuestionsStorePort.getQuestions();
        return questionStoreMapper.of(questionStores);
    }

    @Override
    public void delete(Long id) {
        QuestionStore questionStore = getQuestionsStorePort.getQuestionStore(id);
        checkDifferentUser(questionStore.getUser());
        deleteQuestionStorePort.delete(questionStore);
    }

    private void checkDifferentUser(User writer) {
        User user = getUserPort.getCurrentUser();
        if(user.isNotCorrectEmail(writer.getEmail())) {
            throw new DifferentUserException();
        }
    }

    @Override
    public void delete() {
        deleteQuestionStorePort.delete();
    }
}
