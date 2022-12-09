package taskdb.taskdb.application.question.port.in;

import taskdb.taskdb.application.question.dto.QuestionUpdateRequestDto;

public interface QuestionUpdateUseCase {
    void update(Long id, QuestionUpdateRequestDto requestDto);
}
