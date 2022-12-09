package taskdb.taskdb.application.like.port.out;

import taskdb.taskdb.domain.like.entity.QuestionLike;

public interface SaveQuestionLikePort {
    void save(QuestionLike questionLike);
}
