package taskdb.taskdb.application.answer.port.in;

import taskdb.taskdb.application.answer.dto.AnswerCreateRequestDto;

import java.util.UUID;

public interface AnswerSaveUseCase {
    void save(UUID id, AnswerCreateRequestDto requestDto);
}
