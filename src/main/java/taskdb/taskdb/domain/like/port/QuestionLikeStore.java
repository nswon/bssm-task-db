package taskdb.taskdb.domain.like.port;

import taskdb.taskdb.domain.like.domain.QuestionLike;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.user.domain.User;

public interface QuestionLikeStore {
    QuestionLike store(QuestionLike questionLike);
    void delete(Question question, User user);
}
