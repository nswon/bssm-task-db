package taskdb.taskdb.domain.user.exception;

import taskdb.taskdb.global.exception.BadRequestException;

public class OverflowNicknameException extends BadRequestException {
    private static final String MESSAGE = "닉네임의 범위가 초과되었습니다.";

    public OverflowNicknameException() {
        super(MESSAGE);
    }
}
