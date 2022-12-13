package taskdb.taskdb.application.questionLike.port.out;

import taskdb.taskdb.domain.questionLike.entity.QuestionLike;
import taskdb.taskdb.domain.question.entity.Question;

import java.util.List;

public interface GetQuestionLikePort {
    List<QuestionLike> getQuestionLike(Question question);
}
