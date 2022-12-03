package taskdb.taskdb.domain.question.exception;

import taskdb.taskdb.global.exception.BadRequestException;

public class InvalidTitleRangeException extends BadRequestException {
    private static final String MESSAGE = "잘못된 제목의 범위입니다.";

    public InvalidTitleRangeException() {
        super(MESSAGE);
    }
}
