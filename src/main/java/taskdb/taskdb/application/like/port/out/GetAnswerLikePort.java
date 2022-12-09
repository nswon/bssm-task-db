package taskdb.taskdb.application.like.port.out;

import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.like.entity.AnswerLike;

import java.util.List;

public interface GetAnswerLikePort {
    List<AnswerLike> getAnswerLike(Answer answer);
}
