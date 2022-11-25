package taskdb.taskdb.domain.store.presentation.dto.response;

import lombok.Getter;
import taskdb.taskdb.domain.store.domain.QuestionStore;

@Getter
public class StoreQuestionsResponseDto {
    private final Long id;
    private final String title;

    public StoreQuestionsResponseDto(QuestionStore questionStore) {
        this.id = questionStore.getQuestionId();
        this.title = questionStore.getQuestionTitle();
    }
}
