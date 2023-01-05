package taskdb.taskdb.application.question.port.in;

import taskdb.taskdb.application.question.dto.QuestionUpdateRequestDto;

import java.util.UUID;

public interface QuestionUpdateUseCase {
    void update(UUID id, QuestionUpdateRequestDto requestDto);
}
