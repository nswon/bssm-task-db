package taskdb.taskdb.domain.store.dto;

import lombok.Getter;
import taskdb.taskdb.domain.store.domain.QuestionStore;

import java.util.List;

@Getter
public class QuestionStoresResponseDto {
    List<QuestionStoreResponseDto> questionStores;

    public QuestionStoresResponseDto() {
    }

    public QuestionStoresResponseDto(List<QuestionStoreResponseDto> questionStores) {
        this.questionStores = questionStores;
    }
}
