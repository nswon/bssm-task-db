package taskdb.taskdb.application.like.port.out;

import taskdb.taskdb.domain.like.entity.QuestionLike;
import taskdb.taskdb.domain.question.entity.Question;

import java.util.List;

public interface GetQuestionLikePort {
    List<QuestionLike> getQuestionLike(Question question);
}
