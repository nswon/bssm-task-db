package taskdb.taskdb.adapter.question.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.application.question.port.out.DeleteQuestionPort;
import taskdb.taskdb.application.question.port.out.GetQuestionPort;
import taskdb.taskdb.application.question.port.out.SaveQuestionPort;
import taskdb.taskdb.domain.question.entity.Category;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.adapter.question.out.persistence.QuestionRepository;
import taskdb.taskdb.domain.question.entity.QuestionStatus;
import taskdb.taskdb.domain.question.exception.QuestionNotFoundException;

import java.util.List;

@RequiredArgsConstructor
public class QuestionAdapter implements SaveQuestionPort, GetQuestionPort, DeleteQuestionPort {
    private final QuestionRepository questionRepository;
    private final QuestionQuerydslRepository questionQuerydslRepository;

    @Override
    public Question save(Question question) {
        questionRepository.save(question);
        return question;
    }

    @Override
    public void delete(Question question) {
        questionRepository.delete(question);
    }

    @Override
    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestion(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(QuestionNotFoundException::new);
    }

    @Override
    public List<Question> getQuestionsByKeyword(String keyword) {
        return questionQuerydslRepository.getQuestionByKeyword(keyword);
    }

    @Override
    public List<Question> getQuestionsByCategory(String command) {
        Category category = Category.toEnum(command);
        return questionRepository.findByCategory(category);
    }

    @Override
    public List<Question> getQuestionsByGrade(int grade) {
        return questionQuerydslRepository.getQuestionByGrade(grade);
    }

    @Override
    public List<Question> getQuestionsByStatus(String command) {
        QuestionStatus status = QuestionStatus.toEnum(command);
        return questionQuerydslRepository.getQuestionByStatus(status);
    }
}
