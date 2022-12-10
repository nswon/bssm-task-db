package taskdb.taskdb.infrastructure.security.exception;

import taskdb.taskdb.infrastructure.exception.BadRequestException;

public class ExpiredTokenException extends BadRequestException {
    private static final String MESSAGE = "만료시간이 다 되었습니다. 다시 로그인 해 주세요.";

    public ExpiredTokenException() {
        super(MESSAGE);
    }
}
