package taskdb.taskdb.application.answerLike.port.out;

import taskdb.taskdb.domain.answer.entity.Answer;
import taskdb.taskdb.domain.answerLike.entity.AnswerLike;

import java.util.List;

public interface GetAnswerLikePort {
    List<AnswerLike> getAnswerLike(Answer answer);
}
