package taskdb.taskdb.application.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.question.port.out.GetQuestionPort;
import taskdb.taskdb.application.store.dto.QuestionStoreMapper;
import taskdb.taskdb.application.store.dto.QuestionStoresResponseDto;
import taskdb.taskdb.application.store.port.in.DeleteQuestionStoreUseCase;
import taskdb.taskdb.application.store.port.in.GetQuestionStoreUseCase;
import taskdb.taskdb.application.store.port.in.QuestionStoreSaveUseCase;
import taskdb.taskdb.application.store.port.out.DeleteQuestionStorePort;
import taskdb.taskdb.application.store.port.out.GetQuestionStorePort;
import taskdb.taskdb.application.store.port.out.SaveQuestionStorePort;
import taskdb.taskdb.application.user.port.out.GetUserPort;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.store.entity.QuestionStore;
import taskdb.taskdb.domain.user.entity.User;

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
        QuestionStore questionStore = questionStoreMapper.toEntity(question, user);
        saveQuestionStorePort.save(user, questionStore);
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionStoresResponseDto getQuestions() {
        User user = getUserPort.getCurrentUser();
        List<QuestionStore> questionStores = getQuestionsStorePort.getQuestions(user);
        return questionStoreMapper.of(questionStores);
    }

    @Override
    public void delete(Long id) {
        User user = getUserPort.getCurrentUser();
        QuestionStore questionStore = getQuestionsStorePort.getQuestionStore(user, id);
        deleteQuestionStorePort.delete(questionStore);
    }

    @Override
    public void delete() {
        deleteQuestionStorePort.delete();
    }
}
