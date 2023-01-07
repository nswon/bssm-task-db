package taskdb.taskdb.application.answer.port.in;

import taskdb.taskdb.application.answer.dto.AnswerUpdateRequestDto;

import java.util.UUID;

public interface AnswerUpdateUseCase {
    void update(UUID id, AnswerUpdateRequestDto requestDto);
}
