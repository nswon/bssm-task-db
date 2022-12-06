package taskdb.taskdb.infrastructure.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.question.domain.Category;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.question.domain.QuestionStatus;
import taskdb.taskdb.domain.question.exception.QuestionNotFoundException;
import taskdb.taskdb.domain.question.port.QuestionReader;
import taskdb.taskdb.domain.question.repository.QuestionQuerydslRepository;
import taskdb.taskdb.domain.question.repository.QuestionRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionReaderImpl implements QuestionReader {
    private final QuestionRepository questionRepository;
    private final QuestionQuerydslRepository questionQuerydslRepository;

    @Override
    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestionById(Long id) {
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
