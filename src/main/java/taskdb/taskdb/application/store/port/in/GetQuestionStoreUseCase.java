package taskdb.taskdb.application.store.port.in;

import taskdb.taskdb.application.store.dto.QuestionStoresResponseDto;

public interface GetQuestionStoreUseCase {
    QuestionStoresResponseDto getQuestions();
}
