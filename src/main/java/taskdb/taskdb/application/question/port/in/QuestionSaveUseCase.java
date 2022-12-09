package taskdb.taskdb.application.question.port.in;

import taskdb.taskdb.application.question.dto.QuestionCreateRequestDto;
import taskdb.taskdb.domain.question.entity.Question;

public interface QuestionSaveUseCase {
    Question save(QuestionCreateRequestDto requestDto);
}
