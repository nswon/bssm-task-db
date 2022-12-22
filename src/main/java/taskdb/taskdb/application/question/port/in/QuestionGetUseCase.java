package taskdb.taskdb.application.question.port.in;

import taskdb.taskdb.application.question.dto.QuestionDetailResponse;
import taskdb.taskdb.application.question.dto.QuestionAllResponseDto;

import java.util.List;

public interface QuestionGetUseCase {
    List<QuestionAllResponseDto> getQuestions();
    QuestionDetailResponse getQuestion(Long id);
    List<QuestionAllResponseDto> getQuestionsByCategory(String category);
    List<QuestionAllResponseDto> getQuestionsByStatus(String status);
    List<QuestionAllResponseDto> getQuestionsByKeyword(String keyword);
    List<QuestionAllResponseDto> getQuestionsByGrade(int grade);
}
