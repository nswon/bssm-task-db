package taskdb.taskdb.domain.user.exception;

import taskdb.taskdb.global.exception.BadRequestException;

public class NotBsmEmailException extends BadRequestException {
    private static final String MESSAGE = "부산소프트웨어 마이스터고 이메일이 아닙니다.";

    public NotBsmEmailException() {
        super(MESSAGE);
    }
}
