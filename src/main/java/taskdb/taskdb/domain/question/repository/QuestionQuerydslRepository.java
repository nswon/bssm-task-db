package taskdb.taskdb.domain.question.repository;

import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.question.domain.QuestionStatus;

import java.util.List;

public interface QuestionQuerydslRepository {
    List<Question> getQuestionByKeyword(String keyword);
    List<Question> getQuestionByGrade(int grade);
    List<Question> getQuestionByStatus(QuestionStatus status);
}
