package taskdb.taskdb.domain.question.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.question.domain.Category;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.question.repository.QuestionRepository;
import taskdb.taskdb.domain.question.exception.QuestionNotFoundException;
import taskdb.taskdb.domain.question.service.VisitQuestionService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionFacade {
    private static final String ROUND_FORMAT = "%.2f";
    private final QuestionRepository questionRepository;
    private final VisitQuestionService visitQuestionService;

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(QuestionNotFoundException::new);
    }

    public void addViewCountByVisit(Question question) {
        List<String> questionIds = visitQuestionService.getQuestionIds();
        String questionId = String.valueOf(question.getId());
        if(canAddViewCount(questionIds, questionId)) {
            visitQuestionService.addQuestionId(questionId);
            question.addViewCount();
        }
    }

    private boolean canAddViewCount(List<String> questionIds, String questionId) {
        return questionIds.isEmpty() || questionIds.stream()
                .noneMatch(id -> id.equals(questionId));
    }

    public String getRate(double questionCount, Category category) {
        double subjectCount = getCountByCategory(category);
        double rate = subjectCount/questionCount * 100;
        return String.format(ROUND_FORMAT, rate);
    }

    private double getCountByCategory(Category category) {
        return 1.0 * (int) questionRepository.findAll().stream()
                .filter(question -> question.getCategory().equals(category))
                .count();
    }
}
