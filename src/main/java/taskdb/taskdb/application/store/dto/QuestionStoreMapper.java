package taskdb.taskdb.application.store.dto;

import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.question.entity.Title;
import taskdb.taskdb.domain.store.entity.QuestionStore;
import taskdb.taskdb.domain.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionStoreMapper {

    public QuestionStore toEntity(Question question, User user) {
        QuestionStore questionStore = QuestionStore.builder()
                .questionId(question.getId())
                .questionTitle(question.getTitle())
                .build();
        questionStore.confirmUser(user);
        return questionStore;
    }

    public QuestionStoresResponseDto of(List<QuestionStore> questionStores) {
        List<QuestionStoreResponseDto> questionStoreResponses = getQuestionStoreResponses(questionStores);
        return new QuestionStoresResponseDto(questionStoreResponses);
    }

    private List<QuestionStoreResponseDto> getQuestionStoreResponses(List<QuestionStore> questionStores) {
        return questionStores.stream()
                .map(QuestionStoreResponseDto::new)
                .collect(Collectors.toList());
    }
}
