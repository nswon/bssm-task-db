package taskdb.taskdb.application.answerLike.port.out;

import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.user.entity.User;

public interface ExistAnswerLikePort {
    boolean hasAnswerLike(Answer answer, User user);
}
