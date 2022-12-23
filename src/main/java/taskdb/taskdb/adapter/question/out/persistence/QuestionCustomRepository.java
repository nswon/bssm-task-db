package taskdb.taskdb.adapter.question.out.persistence;

import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.question.entity.QuestionStatus;

import java.util.List;

public interface QuestionCustomRepository {
    List<Question> getQuestionByKeyword(String keyword);
    List<Question> getQuestionByGrade(int grade);
    List<Question> getQuestionByStatus(QuestionStatus status);
}
