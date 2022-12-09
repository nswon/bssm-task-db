package taskdb.taskdb.application.like.port.out;

import taskdb.taskdb.domain.like.entity.AnswerLike;

public interface SaveAnswerLikePort {
    AnswerLike save(AnswerLike answerLike);
}
