package taskdb.taskdb.infrastructure.security.exception;

import taskdb.taskdb.infrastructure.exception.BadRequestException;

public class InvalidTokenException extends BadRequestException {
    private static final String MESSAGE = "올바른 토큰이 아닙니다.";

    public InvalidTokenException() {
        super(MESSAGE);
    }
}
