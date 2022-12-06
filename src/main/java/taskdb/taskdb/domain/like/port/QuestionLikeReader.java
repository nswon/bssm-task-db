package taskdb.taskdb.domain.like.port;

import taskdb.taskdb.domain.like.domain.QuestionLike;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.user.domain.User;

import java.util.List;

public interface QuestionLikeReader {
    boolean hasByQuestionAndUser(Question question, User user);
    List<QuestionLike> getQuestionLikeByQuestion(Question question);
}
