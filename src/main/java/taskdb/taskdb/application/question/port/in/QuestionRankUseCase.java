package taskdb.taskdb.application.question.port.in;

import taskdb.taskdb.application.question.dto.QuestionsRankResponseDto;

import java.util.List;

public interface QuestionRankUseCase {
    List<QuestionsRankResponseDto> rank();
}
