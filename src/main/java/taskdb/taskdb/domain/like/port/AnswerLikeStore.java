package taskdb.taskdb.domain.like.port;

import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.like.domain.AnswerLike;
import taskdb.taskdb.domain.user.domain.User;

public interface AnswerLikeStore {
    AnswerLike store(AnswerLike answerLike);
    void delete(Answer answer, User user);
}
