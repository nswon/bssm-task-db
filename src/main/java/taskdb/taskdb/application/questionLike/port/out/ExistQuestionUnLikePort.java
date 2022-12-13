package taskdb.taskdb.application.questionLike.port.out;

import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.user.entity.User;

public interface ExistQuestionUnLikePort {
    boolean hasQuestionUnLike(Question question, User user);
}
