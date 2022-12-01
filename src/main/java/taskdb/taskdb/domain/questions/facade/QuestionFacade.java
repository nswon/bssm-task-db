package taskdb.taskdb.domain.questions.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.questions.domain.Question;
import taskdb.taskdb.domain.questions.domain.QuestionRepository;
import taskdb.taskdb.domain.questions.exception.QuestionException;
import taskdb.taskdb.domain.questions.exception.QuestionExceptionType;
import taskdb.taskdb.global.redis.RedisService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionFacade {
    private final QuestionRepository questionRepository;
    private final RedisService redisService;

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionException(QuestionExceptionType.NOT_FOUND_QUESTION));
    }

    public void addViewCountByVisit(Question question) {
        List<String> questionIds = redisService.getQuestionIds();
        String questionId = String.valueOf(question.getId());
        if(canAddViewCount(questionIds, questionId)) {
            redisService.addQuestionId(questionId);
            question.addViewCount();
        }
    }

    private boolean canAddViewCount(List<String> questionIds, String questionId) {
        return questionIds.isEmpty() || questionIds.stream()
                .noneMatch(id -> id.equals(questionId));
    }
}
