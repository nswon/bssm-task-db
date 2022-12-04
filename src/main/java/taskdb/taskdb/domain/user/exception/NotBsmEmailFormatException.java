package taskdb.taskdb.domain.user.exception;

import taskdb.taskdb.global.exception.BadRequestException;

public class NotBsmEmailFormatException extends BadRequestException {
    private static final String MESSAGE = "올바른 학교 이메일 양식이 아닙니다.";

    public NotBsmEmailFormatException() {
        super(MESSAGE);
    }
}
