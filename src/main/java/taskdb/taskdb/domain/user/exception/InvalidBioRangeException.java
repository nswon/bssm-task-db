package taskdb.taskdb.domain.user.exception;

import taskdb.taskdb.global.exception.BadRequestException;

public class InvalidBioRangeException extends BadRequestException {
    private static final String MESSAGE = "소개는 최소 1글자에서 100글자까지여야 합니다.";

    public InvalidBioRangeException() {
        super(MESSAGE);
    }
}
