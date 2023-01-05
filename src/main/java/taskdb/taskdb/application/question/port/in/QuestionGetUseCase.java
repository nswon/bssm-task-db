package taskdb.taskdb.application.question.port.in;

import taskdb.taskdb.application.question.dto.QuestionDetailResponse;
import taskdb.taskdb.application.question.dto.QuestionAllResponseDto;

import java.util.List;
import java.util.UUID;

public interface QuestionGetUseCase {
    List<QuestionAllResponseDto> getQuestions();
    QuestionDetailResponse getQuestion(UUID id);
    List<QuestionAllResponseDto> getQuestionsByCategory(String category);
    List<QuestionAllResponseDto> getQuestionsByStatus(String status);
    List<QuestionAllResponseDto> getQuestionsByKeyword(String keyword);
    List<QuestionAllResponseDto> getQuestionsByGrade(int grade);
}
