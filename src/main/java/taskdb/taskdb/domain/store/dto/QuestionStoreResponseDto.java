package taskdb.taskdb.domain.store.dto;

import lombok.Getter;
import taskdb.taskdb.domain.store.domain.QuestionStore;

@Getter
public class QuestionStoreResponseDto {
    private Long id;
    private String title;

    public QuestionStoreResponseDto() {
    }

    public QuestionStoreResponseDto(QuestionStore questionStore) {
        this.id = questionStore.getQuestionId();
        this.title = questionStore.getTitle();
    }
}
