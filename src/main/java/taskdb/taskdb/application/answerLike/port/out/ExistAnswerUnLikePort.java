package taskdb.taskdb.application.answerLike.port.out;

import taskdb.taskdb.domain.answer.entity.Answer;
import taskdb.taskdb.domain.user.entity.User;

public interface ExistAnswerUnLikePort {
    boolean hasAnswerUnLike(Answer answer, User user);
}
