package taskdb.taskdb.domain.like.port;

import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.like.domain.AnswerLike;
import taskdb.taskdb.domain.user.domain.User;

import java.util.List;

public interface AnswerLikeReader {
    boolean hasByAnswerAndUser(Answer answer, User user);
    List<AnswerLike> getAnswerLikesByAnswer(Answer answer);
}
