package taskdb.taskdb.domain.question.exception;

import taskdb.taskdb.infrastructure.exception.BadRequestException;

public class InvalidContentRangeException extends BadRequestException {
    private static final String MESSAGE = "잘못된 내용의 범위입니다.";

    public InvalidContentRangeException() {
        super(MESSAGE);
    }
}
