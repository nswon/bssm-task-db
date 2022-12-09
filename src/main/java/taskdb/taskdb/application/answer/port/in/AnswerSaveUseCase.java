package taskdb.taskdb.application.answer.port.in;

import taskdb.taskdb.application.answer.dto.AnswerCreateRequestDto;

public interface AnswerSaveUseCase {
    void save(Long id, AnswerCreateRequestDto requestDto);
}
