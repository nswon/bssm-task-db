package taskdb.taskdb.application.store.dto;

import lombok.Getter;
import taskdb.taskdb.domain.store.entity.QuestionStore;

@Getter
public class QuestionStoreResponseDto {
    private Long id;
    private Long questionId;
    private String title;

    public QuestionStoreResponseDto() {
    }

    public QuestionStoreResponseDto(QuestionStore questionStore) {
        this.id = questionStore.getId();
        this.questionId = questionStore.getQuestionId();
        this.title = questionStore.getTitle();
    }
}
