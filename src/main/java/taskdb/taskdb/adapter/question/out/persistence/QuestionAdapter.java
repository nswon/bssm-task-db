package taskdb.taskdb.adapter.question.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.application.question.port.out.DeleteQuestionPort;
import taskdb.taskdb.application.question.port.out.GetQuestionPort;
import taskdb.taskdb.application.question.port.out.SaveQuestionPort;
import taskdb.taskdb.domain.question.entity.Category;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.question.entity.QuestionStatus;
import taskdb.taskdb.domain.question.exception.QuestionNotFoundException;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class QuestionAdapter implements SaveQuestionPort, GetQuestionPort, DeleteQuestionPort {
    private final QuestionRepository questionRepository;
    private final QuestionCustomRepository questionCustomRepository;

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
    public Question getQuestion(UUID id) {
        return questionRepository.findById(id)
                .orElseThrow(QuestionNotFoundException::new);
    }

    @Override
    public List<Question> getQuestionsByKeyword(String keyword) {
        return questionCustomRepository.getQuestionByKeyword(keyword);
    }

    @Override
    public List<Question> getQuestionsByCategory(String command) {
        Category category = Category.toEnum(command);
        return questionRepository.findByCategory(category);
    }

    @Override
    public List<Question> getQuestionsByGrade(int grade) {
        return questionCustomRepository.getQuestionByGrade(grade);
    }

    @Override
    public List<Question> getQuestionsByStatus(String command) {
        QuestionStatus status = QuestionStatus.toEnum(command);
        return questionCustomRepository.getQuestionByStatus(status);
    }
}
