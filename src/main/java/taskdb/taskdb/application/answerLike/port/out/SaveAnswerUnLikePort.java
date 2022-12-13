package taskdb.taskdb.application.answerLike.port.out;

import taskdb.taskdb.domain.answerLike.entity.AnswerUnLike;

public interface SaveAnswerUnLikePort {
    AnswerUnLike save(AnswerUnLike answerUnLike);
}
