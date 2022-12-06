package taskdb.taskdb.domain.question.port;

import taskdb.taskdb.domain.question.domain.Question;

import java.util.List;

public interface QuestionReader {
    List<Question> getQuestions();
    Question getQuestionById(Long id);
    List<Question> getQuestionsByKeyword(String keyword);
    List<Question> getQuestionsByCategory(String command);
    List<Question> getQuestionsByGrade(int grade);
    List<Question> getQuestionsByStatus(String command);
}
