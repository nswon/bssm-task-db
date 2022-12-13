package taskdb.taskdb.application.questionLike.port.out;

import taskdb.taskdb.domain.questionLike.entity.QuestionUnLike;

public interface SaveQuestionUnLikePort {
    QuestionUnLike save(QuestionUnLike questionUnLike);
}
