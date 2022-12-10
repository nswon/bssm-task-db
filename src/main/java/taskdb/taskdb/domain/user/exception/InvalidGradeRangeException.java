package taskdb.taskdb.domain.user.exception;

import taskdb.taskdb.infrastructure.exception.BadRequestException;

public class InvalidGradeRangeException extends BadRequestException {
    private static final String MESSAGE = "1, 2, 3학년 중에 선택해야 합니다.";

    public InvalidGradeRangeException() {
        super(MESSAGE);
    }
}
