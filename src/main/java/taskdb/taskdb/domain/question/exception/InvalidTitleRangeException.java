package taskdb.taskdb.domain.question.exception;

import taskdb.taskdb.global.exception.BadRequestException;

public class InvalidTitleRangeException extends BadRequestException {
    private static final String MESSAGE = "제목은 최소 1글자에서 50글자까지여야 합니다.";

    public InvalidTitleRangeException() {
        super(MESSAGE);
    }
}
