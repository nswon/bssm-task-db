package taskdb.taskdb.domain.user.exception;

import taskdb.taskdb.global.exception.BadRequestException;

public class InvalidAuthCodeException extends BadRequestException {
    private static final String MESSAGE = "잘못된 인증코드입니다.";

    public InvalidAuthCodeException() {
        super(MESSAGE);
    }
}
