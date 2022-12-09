package taskdb.taskdb.application.question.port.in;

import taskdb.taskdb.application.question.dto.QuestionAllResponseDto;
import taskdb.taskdb.application.question.dto.QuestionDetailResponse;

public interface QuestionGetUseCase {
    QuestionAllResponseDto getQuestions();
    QuestionDetailResponse getQuestion(Long id);
    QuestionAllResponseDto getQuestionsByCategory(String category);
    QuestionAllResponseDto getQuestionsByStatus(String status);
    QuestionAllResponseDto getQuestionsByKeyword(String keyword);
    QuestionAllResponseDto getQuestionsByGrade(int grade);
}
