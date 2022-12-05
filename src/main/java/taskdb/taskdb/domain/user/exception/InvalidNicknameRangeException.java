package taskdb.taskdb.domain.user.exception;

import taskdb.taskdb.global.exception.BadRequestException;

public class InvalidNicknameRangeException extends BadRequestException {
    private static final String MESSAGE = "닉네임은 최소 2글자에서 20글자까지여야 합니다.";

    public InvalidNicknameRangeException() {
        super(MESSAGE);
    }
}
