package taskdb.taskdb.domain.answerLike.exception;

import taskdb.taskdb.infrastructure.exception.BadRequestException;

public class DuplicateAnswerUnLikeException extends BadRequestException {
    private static final String MESSAGE = "이미 답변 싫어요를 누른 상태입니다.";

    public DuplicateAnswerUnLikeException() {
        super(MESSAGE);
    }
}
