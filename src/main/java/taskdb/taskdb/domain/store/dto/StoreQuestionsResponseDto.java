package taskdb.taskdb.domain.store.dto;

import lombok.Getter;
import taskdb.taskdb.domain.store.domain.QuestionStore;

@Getter
public class StoreQuestionsResponseDto {
    private Long id;
    private String title;

    public StoreQuestionsResponseDto() {
    }

    public StoreQuestionsResponseDto(QuestionStore questionStore) {
        this.id = questionStore.getQuestionId();
        this.title = questionStore.getTitle();
    }
}
