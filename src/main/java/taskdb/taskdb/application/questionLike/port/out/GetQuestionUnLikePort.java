package taskdb.taskdb.application.questionLike.port.out;

import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.questionLike.entity.QuestionUnLike;

import java.util.List;

public interface GetQuestionUnLikePort {
    List<QuestionUnLike> getQuestionUnLikes(Question question);
}
