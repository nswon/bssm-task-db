package taskdb.taskdb.application.store.dto;

import lombok.Getter;

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
