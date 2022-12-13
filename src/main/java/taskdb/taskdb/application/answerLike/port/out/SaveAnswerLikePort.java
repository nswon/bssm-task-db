package taskdb.taskdb.application.answerLike.port.out;

import taskdb.taskdb.domain.answerLike.entity.AnswerLike;

public interface SaveAnswerLikePort {
    AnswerLike save(AnswerLike answerLike);
}
