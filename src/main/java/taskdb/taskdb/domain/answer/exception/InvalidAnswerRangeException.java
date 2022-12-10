package taskdb.taskdb.domain.answer.exception;

import taskdb.taskdb.infrastructure.exception.BadRequestException;

public class InvalidAnswerRangeException extends BadRequestException {
    private static final String MESSAGE = "답변은 최소 1글자에서 1000글자까지여야 합니다.";

    public InvalidAnswerRangeException() {
        super(MESSAGE);
    }
}
