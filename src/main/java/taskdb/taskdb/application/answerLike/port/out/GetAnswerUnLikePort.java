package taskdb.taskdb.application.answerLike.port.out;

import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answerLike.entity.AnswerUnLike;

import java.util.List;

public interface GetAnswerUnLikePort {
    List<AnswerUnLike> getAnswerUnLikes(Answer answer);
}
