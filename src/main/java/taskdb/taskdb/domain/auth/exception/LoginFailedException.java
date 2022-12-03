package taskdb.taskdb.domain.auth.exception;

import taskdb.taskdb.global.exception.UnauthorizedException;

public class LoginFailedException extends UnauthorizedException {
    private static final String MESSAGE = "아이디나 비밀번호가 일치하지 않습니다.";

    public LoginFailedException() {
        super(MESSAGE);
    }
}
