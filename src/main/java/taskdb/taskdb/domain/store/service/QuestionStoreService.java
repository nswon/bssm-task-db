package taskdb.taskdb.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.question.port.QuestionReader;
import taskdb.taskdb.domain.store.domain.QuestionStore;
import taskdb.taskdb.domain.store.port.QuestionStoreReader;
import taskdb.taskdb.domain.store.port.QuestionStoreStore;
import taskdb.taskdb.domain.store.dto.QuestionStoresResponseDto;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;
import taskdb.taskdb.domain.user.port.UserReader;
import taskdb.taskdb.mapper.QuestionStoreMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionStoreService {
    private final UserReader userReader;
    private final UserFacade userFacade;
    private final QuestionReader questionReader;
    private final QuestionStoreReader questionStoreReader;
    private final QuestionStoreStore questionStoreStore;
    private final QuestionStoreMapper questionStoreMapper;

    public void create(Long id) {
        User user = userReader.getCurrentUser();
        Question question = questionReader.getQuestionById(id);
        QuestionStore questionStore = questionStoreMapper.of(question, user);
        questionStoreStore.store(questionStore);
    }

    @Transactional(readOnly = true)
    public QuestionStoresResponseDto getQuestions() {
        List<QuestionStore> questionStores = questionStoreReader.getQuestions();
        return questionStoreMapper.of(questionStores);
    }

    public void delete(Long id) {
        User user = userReader.getCurrentUser();
        QuestionStore questionStore = questionStoreReader.getQuestionStore(id);
        User writer = questionStore.getUser();
        userFacade.checkDifferentUser(user, writer);
        questionStoreStore.delete(questionStore);
    }

    public void deleteAll() {
        questionStoreStore.deleteAll();
    }
}
