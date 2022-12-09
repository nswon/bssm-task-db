package taskdb.taskdb.application.like.port.out;

import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.user.entity.User;

public interface DeleteQuestionLikePort {
    void delete(Question question, User user);
}
