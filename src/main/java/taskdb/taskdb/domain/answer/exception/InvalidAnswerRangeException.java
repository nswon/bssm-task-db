package taskdb.taskdb.domain.answer.exception;

import taskdb.taskdb.global.exception.BadRequestException;

public class InvalidAnswerRangeException extends BadRequestException {
    private static final String MESSAGE = "잘못된 답변의 범위입니다.";

    public InvalidAnswerRangeException() {
        super(MESSAGE);
    }
}
