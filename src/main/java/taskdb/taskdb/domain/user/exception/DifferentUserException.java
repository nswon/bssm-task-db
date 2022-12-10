package taskdb.taskdb.domain.user.exception;

import taskdb.taskdb.infrastructure.exception.BadRequestException;

public class DifferentUserException extends BadRequestException {
    private static final String MESSAGE = "다른 유저입니다.";

    public DifferentUserException() {
        super(MESSAGE);
    }
}
