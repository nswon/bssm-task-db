package taskdb.taskdb.domain.user.exception;

import taskdb.taskdb.global.exception.BadRequestException;

public class InvalidGradeFormatException extends BadRequestException {
    private static final String MESSAGE = "올바른 학년 형식이 아닙니다.";

    public InvalidGradeFormatException() {
        super(MESSAGE);
    }
}
