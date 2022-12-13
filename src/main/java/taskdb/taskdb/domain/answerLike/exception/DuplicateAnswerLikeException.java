package taskdb.taskdb.domain.answerLike.exception;

import taskdb.taskdb.infrastructure.exception.BadRequestException;

public class DuplicateAnswerLikeException extends BadRequestException {
    private static final String MESSAGE = "이미 답변 좋아요를 누른 상태입니다.";

    public DuplicateAnswerLikeException() {
        super(MESSAGE);
    }
}
