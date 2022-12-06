package taskdb.taskdb.mapper;

import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.question.domain.Title;
import taskdb.taskdb.domain.store.domain.QuestionStore;
import taskdb.taskdb.domain.store.dto.QuestionStoreResponseDto;
import taskdb.taskdb.domain.store.dto.QuestionStoresResponseDto;
import taskdb.taskdb.domain.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionStoreMapper {
    public QuestionStore of(Question question, User user) {
        return QuestionStore.builder()
                .questionId(question.getId())
                .questionTitle(Title.of(question.getTitle()))
                .user(user)
                .build();
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
