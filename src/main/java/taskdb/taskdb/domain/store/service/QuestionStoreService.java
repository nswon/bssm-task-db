package taskdb.taskdb.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.question.facade.QuestionFacade;
import taskdb.taskdb.domain.store.domain.QuestionStore;
import taskdb.taskdb.domain.store.repository.QuestionStoreRepository;
import taskdb.taskdb.domain.store.facade.QuestionStoreFacade;
import taskdb.taskdb.domain.store.dto.StoreQuestionsResponseDto;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionStoreService {
    private final QuestionStoreRepository questionStoreRepository;
    private final UserFacade userFacade;
    private final QuestionFacade questionFacade;
    private final QuestionStoreFacade questionStoreFacade;

    public void create(Long id) {
        User user = userFacade.getCurrentUser();
        Question question = questionFacade.getQuestionById(id);
        QuestionStore questionStore = QuestionStore.builder()
                .questionId(question.getId())
                .questionTitle(question.getTitle().getValue())
                .build();
        questionStore.confirmUser(user);
        questionStoreRepository.save(questionStore);
    }

    @Transactional(readOnly = true)
    public List<StoreQuestionsResponseDto> getQuestions() {
        return questionStoreRepository.findAll().stream()
                .map(StoreQuestionsResponseDto::new)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        User user = userFacade.getCurrentUser();
        QuestionStore store = questionStoreFacade.getQuestionStoreById(id);
        User writer = store.getUser();
        userFacade.checkDifferentUser(user, writer);
        questionStoreRepository.delete(store);
    }

    public void deleteAll() {
        questionStoreRepository.deleteAllInBatch();
    }
}
