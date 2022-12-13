package taskdb.taskdb.application.questionLike.port.out;

import taskdb.taskdb.domain.questionLike.entity.QuestionLike;

public interface SaveQuestionLikePort {
    void save(QuestionLike questionLike);
}
