package taskdb.taskdb.application.answer.port.in;

import taskdb.taskdb.application.answer.dto.AnswerUpdateRequestDto;

public interface AnswerUpdateUseCase {
    void update(Long id, AnswerUpdateRequestDto requestDto);
}
