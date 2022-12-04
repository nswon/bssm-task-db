package taskdb.taskdb.domain.user.exception;

import taskdb.taskdb.global.exception.BadRequestException;

public class InvalidPasswordFormatException extends BadRequestException {
    private static final String MESSAGE = "올바른 비밀번호 양식이 아닙니다.";

    public InvalidPasswordFormatException() {
        super(MESSAGE);
    }
}
