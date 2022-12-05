package taskdb.taskdb.domain.auth.exception;

import taskdb.taskdb.global.exception.BadRequestException;

public class InvalidEmailException extends BadRequestException {
    private static final String MESSAGE = "잘못된 이메일입니다.";

    public InvalidEmailException() {
        super(MESSAGE);
    }
}
