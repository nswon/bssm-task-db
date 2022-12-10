package taskdb.taskdb.domain.user.exception;

import taskdb.taskdb.infrastructure.exception.BadRequestException;

public class UserNotFoundException extends BadRequestException {
    private static final String MESSAGE = "유저가 존재하지 않습니다.";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
