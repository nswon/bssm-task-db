package taskdb.taskdb.domain.user.exception;

import taskdb.taskdb.infrastructure.exception.UnauthorizedException;

public class AuthorizationException extends UnauthorizedException {
    private static final String MESSAGE = "로그인 후 이용가능합니다.";

    public AuthorizationException() {
        super(MESSAGE);
    }
}
