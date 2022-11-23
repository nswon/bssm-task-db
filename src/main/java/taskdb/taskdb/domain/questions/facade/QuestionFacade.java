package taskdb.taskdb.domain.questions.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.questions.domain.Question;
import taskdb.taskdb.domain.questions.domain.QuestionRepository;
import taskdb.taskdb.domain.questions.exception.QuestionException;
import taskdb.taskdb.domain.questions.exception.QuestionExceptionType;
import taskdb.taskdb.domain.user.domain.User;

@Component
@RequiredArgsConstructor
public class QuestionFacade {
    private final QuestionRepository questionRepository;

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionException(QuestionExceptionType.NOT_FOUND_QUESTION));
    }

    public void checkDifferentUser(User user, String email) {
        if(user.isNotCorrectEmail(email)) {
            throw new QuestionException(QuestionExceptionType.DIFFERENT_USER);
        }
    }
}
